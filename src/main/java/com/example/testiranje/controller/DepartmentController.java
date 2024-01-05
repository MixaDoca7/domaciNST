package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.dto.DepartmentDto;
import com.example.testiranje.controller.repository.RepositoryDepartment;
import com.example.testiranje.controller.service.DepartmentService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ResponseCache;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(@Qualifier(value = "repositoryService") DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> save(@Valid @RequestBody DepartmentDto departmentDto) throws Exception {
        DepartmentDto deptartmentDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(deptartmentDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        List<Department> deptList = departmentService.getAll();
        return new ResponseEntity<>(deptList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable("id") Long id){
        Department dept = departmentService.findById(id);
        if(dept != null) {
            return new ResponseEntity<>(dept, HttpStatus.FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("greska", "Ne postoji ovaj indeks");
        return new ResponseEntity<>(dept,headers,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return departmentService.delete(id);
    }


}

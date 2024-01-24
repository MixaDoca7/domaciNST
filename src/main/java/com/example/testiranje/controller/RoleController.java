package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.Role;
import com.example.testiranje.controller.service.impl.RoleRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleRepositoryImpl roleRepository;

    public RoleController(RoleRepositoryImpl roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public void save(@RequestBody Role role){
        roleRepository.save(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> delete(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(roleRepository.delete(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Role>> getAll(){
        return new ResponseEntity<>(roleRepository.getAll(),HttpStatus.FOUND);
    }

    @GetMapping("/find{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(roleRepository.findById(id),HttpStatus.FOUND);
    }
}

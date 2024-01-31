package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.service.impl.HohRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/HistoryOfHeads")
public class HohController {

    private final HohRepositoryImpl hohRepository;


    public HohController(HohRepositoryImpl hohRepository) {
        this.hohRepository = hohRepository;
    }

    @PostMapping("/add/{id}/{startDate}/{endDate}")
    public void save(@PathVariable(name = "id")Long id, @PathVariable(name = "startDate")LocalDate start,
                     @PathVariable(name = "endDate") LocalDate end) throws Exception {
        hohRepository.save(id,start,end);
    }
    @GetMapping("/getAllForMember/{id}")
    public ResponseEntity<List<HistoryOfHeads>> getAllMember(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(hohRepository.getAllMember(id).get(), HttpStatus.FOUND);
    }

    @GetMapping("/getAllForDepartment/{id}")
    public ResponseEntity<List<HistoryOfHeads>> getAllDepartment(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(hohRepository.getAllDepartment(id).get(), HttpStatus.FOUND);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody HistoryOfHeads hoh) throws Exception {
        hohRepository.update(id,hoh);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        return hohRepository.delete(id);
    }


}

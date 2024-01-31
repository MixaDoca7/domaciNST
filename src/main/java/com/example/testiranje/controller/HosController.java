package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.domane.HistoryOfSecretary;
import com.example.testiranje.controller.service.impl.HosRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("HistoryOfSecretary")
public class HosController {

    private final HosRepositoryImpl hosRepository;

    public HosController(HosRepositoryImpl hosRepository) {
        this.hosRepository = hosRepository;
    }

    @PostMapping("/add/{id}/{startDate}/{endDate}")
    public void save(@PathVariable(name = "id")Long id, @PathVariable(name = "startDate") LocalDate start,
                     @PathVariable(name = "endDate") LocalDate end) throws Exception {
        hosRepository.save(id,start,end);
    }
    @GetMapping("/getAllForMember/{id}")
    public ResponseEntity<List<HistoryOfSecretary>> getAllMember(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(hosRepository.getAllMember(id).get(), HttpStatus.FOUND);
    }

    @GetMapping("/getAllForDepartment/{id}")
    public ResponseEntity<List<HistoryOfSecretary>> getAllDepartment(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(hosRepository.getAllDepartment(id).get(), HttpStatus.FOUND);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody HistoryOfHeads hoh) throws Exception {
        hosRepository.update(id,hoh);
        return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        return hosRepository.delete(id);
    }

}

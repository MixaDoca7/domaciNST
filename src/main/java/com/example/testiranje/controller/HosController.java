package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.domane.HistoryOfSecretary;
import com.example.testiranje.controller.service.impl.HosRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("HistoryOfSecretary")
public class HosController {

    private final HosRepositoryImpl hosRepository;

    public HosController(HosRepositoryImpl hosRepository) {
        this.hosRepository = hosRepository;
    }

    @PostMapping("/add/{id}")
    public void save(@PathVariable(name = "id")Long id, @RequestBody Department department) throws Exception {
        hosRepository.save(id,department);
    }
    @GetMapping("/getAllFor/{id}")
    public ResponseEntity<List<HistoryOfSecretary>> getAll(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(hosRepository.getAll(id).get(), HttpStatus.FOUND);
    }

}

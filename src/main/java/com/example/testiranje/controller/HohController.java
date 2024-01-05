package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.dto.MemberDto;
import com.example.testiranje.controller.service.impl.HohRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/HistoryOfHeads")
public class HohController {

    private final HohRepositoryImpl hohRepository;


    public HohController(HohRepositoryImpl hohRepository) {
        this.hohRepository = hohRepository;
    }

    @PostMapping("/add/{id}")
    public void save(@PathVariable(name = "id")Long id, @RequestBody Department department) throws Exception {
        hohRepository.save(id,department);
    }
    @GetMapping("/getAllFor/{id}")
    public ResponseEntity<List<HistoryOfHeads>> getAll(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(hohRepository.getAll(id).get(), HttpStatus.FOUND);
    }
}

package com.example.testiranje.controller;

import com.example.testiranje.controller.dto.SubjectDto;
import com.example.testiranje.controller.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController{

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<SubjectDto> save(@Valid @RequestBody SubjectDto subject) throws Exception {
        SubjectDto subjectDto = subjectService.save(subject);
        return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAll() {
        List<SubjectDto> list = subjectService.getAll();
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        subjectService.delete(id);
    }

    public SubjectDto findById(Long id) throws Exception {
        return subjectService.findById(id);
    }
}

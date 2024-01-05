package com.example.testiranje.controller.service;

import com.example.testiranje.controller.dto.SubjectDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SubjectService {

    SubjectDto save(SubjectDto subjectDto) throws Exception;
    List<SubjectDto> getAll();
    void delete(Long id) throws Exception;
    SubjectDto findById(Long id) throws Exception;




}

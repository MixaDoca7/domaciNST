package com.example.testiranje.controller.service;

import com.example.testiranje.controller.domane.AcademicTitleHistory;

import java.util.List;

public interface AthService {

    void changeAcademicTitle(Long id,String title);
    List<AcademicTitleHistory> getAll(Long id) throws Exception;
}

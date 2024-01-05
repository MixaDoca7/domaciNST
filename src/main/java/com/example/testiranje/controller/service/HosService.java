package com.example.testiranje.controller.service;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.HistoryOfSecretary;

import java.util.List;
import java.util.Optional;

public interface HosService {

    public void save(Long id, Department department) throws Exception;
    public Optional<List<HistoryOfSecretary>> getAll(Long id) throws Exception;
}

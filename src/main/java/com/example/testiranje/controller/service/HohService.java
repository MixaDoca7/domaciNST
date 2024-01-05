package com.example.testiranje.controller.service;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface HohService {

    public void save(Long id, Department department) throws Exception;
    public Optional<List<HistoryOfHeads>> getAll(Long id) throws Exception;

}

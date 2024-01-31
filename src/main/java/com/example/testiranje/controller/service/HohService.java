package com.example.testiranje.controller.service;

import com.example.testiranje.controller.domane.HistoryOfHeads;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HohService {

    void save(Long id, LocalDate start, LocalDate end) throws Exception;
    Optional<List<HistoryOfHeads>> getAllMember(Long id) throws Exception;

    Optional<List<HistoryOfHeads>> getAllDepartment(Long id) throws Exception;

    void update(Long id, HistoryOfHeads hoh) throws Exception;

    String delete(Long id);
}

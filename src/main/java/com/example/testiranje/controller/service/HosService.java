package com.example.testiranje.controller.service;

import com.example.testiranje.controller.domane.HistoryOfHeads;
import com.example.testiranje.controller.domane.HistoryOfSecretary;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HosService {

    void save(Long id, LocalDate start, LocalDate end) throws Exception;
    String delete(Long id);
    Optional<List<HistoryOfSecretary>> getAllMember(Long id) throws Exception;
    Optional<List<HistoryOfSecretary>> getAllDepartment(Long id) throws Exception;
    void update(Long id, HistoryOfHeads hoh) throws Exception;


}

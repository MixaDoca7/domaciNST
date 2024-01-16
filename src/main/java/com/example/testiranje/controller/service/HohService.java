package com.example.testiranje.controller.service;

import com.example.testiranje.controller.domane.HistoryOfHeads;

import java.util.List;
import java.util.Optional;

public interface HohService {

    void save(Long id) throws Exception;
    Optional<List<HistoryOfHeads>> getAll(Long id) throws Exception;

}

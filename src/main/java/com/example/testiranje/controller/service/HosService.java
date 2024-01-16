package com.example.testiranje.controller.service;

import com.example.testiranje.controller.domane.HistoryOfSecretary;

import java.util.List;
import java.util.Optional;

public interface HosService {

    void save(Long id) throws Exception;
    Optional<List<HistoryOfSecretary>> getAll(Long id) throws Exception;
}

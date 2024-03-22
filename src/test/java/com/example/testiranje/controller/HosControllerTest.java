package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.HistoryOfSecretary;
import com.example.testiranje.controller.service.impl.HosRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HosControllerTest {

    @Mock
    HosRepositoryImpl hosRepository;

    @InjectMocks
    HosController hosController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() throws Exception {
        Long id = 1L;
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 12, 31);

        hosController.save(id, startDate, endDate);

        verify(hosRepository, times(1)).save(id, startDate, endDate);
    }

    @Test
    void testGetAllMember() throws Exception {
        Long id = 1L;
        List<HistoryOfSecretary> historyList = new ArrayList<>();
        historyList.add(new HistoryOfSecretary());

        when(hosRepository.getAllMember(id)).thenReturn(Optional.of(historyList));

        ResponseEntity<List<HistoryOfSecretary>> responseEntity = hosController.getAllMember(id);

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(historyList, responseEntity.getBody());
    }

    @Test
    void testGetAllDepartment() throws Exception {
        Long id = 1L;
        List<HistoryOfSecretary> historyList = new ArrayList<>();
        historyList.add(new HistoryOfSecretary());

        when(hosRepository.getAllDepartment(id)).thenReturn(Optional.of(historyList));

        ResponseEntity<List<HistoryOfSecretary>> responseEntity = hosController.getAllDepartment(id);

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(historyList, responseEntity.getBody());
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        HistoryOfSecretary history = new HistoryOfSecretary();

        ResponseEntity<String> responseEntity = hosController.update(id, history);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Successfully updated", responseEntity.getBody());
        verify(hosRepository, times(1)).update(id, history);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        when(hosRepository.delete(id)).thenReturn("Deleted successfully");

        String result = hosController.delete(id);

        assertEquals("Deleted successfully", result);
        verify(hosRepository, times(1)).delete(id);
    }
}

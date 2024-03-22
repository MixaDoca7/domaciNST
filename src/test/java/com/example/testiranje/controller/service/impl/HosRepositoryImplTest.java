package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.domane.*;
import com.example.testiranje.controller.repository.RepositoryDepartment;
import com.example.testiranje.controller.repository.RepositoryHistoryOfSecretary;
import com.example.testiranje.controller.repository.RepositoryMember;
import com.example.testiranje.controller.repository.RepositoryRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class HosRepositoryImplTest {

    @Mock
    private RepositoryMember repositoryMember;

    @Mock
    private RepositoryHistoryOfSecretary repositoryHistoryOfSecretary;

    @Mock
    private RepositoryRole repositoryRole;

    @Mock
    private RepositoryDepartment repositoryDepartment;


    private HosRepositoryImpl hosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hosService = new HosRepositoryImpl(repositoryMember, repositoryHistoryOfSecretary, repositoryRole, repositoryDepartment);
    }

    @Test
    void testSave() throws Exception {
        // Mocking dependencies
        when(repositoryMember.findById(anyLong())).thenReturn(Optional.of(new Member()));
        when(repositoryRole.findByName("Secretary")).thenReturn(new Role("Secretaty"));
        when(repositoryHistoryOfSecretary.findTopByDepartmentOrderByEndtOfPositionDesc(any())).thenReturn(Optional.empty());

        // Test save method
        hosService.save(1L, LocalDate.now(), LocalDate.now().plusDays(1));

        // Verify that repository save method is called once
        verify(repositoryHistoryOfSecretary, times(1)).save(any(HistoryOfSecretary.class));
    }

    @Test
    void testGetAllMember() throws Exception {
        // Mocking dependencies
        when(repositoryMember.findById(anyLong())).thenReturn(Optional.of(new Member()));
        when(repositoryHistoryOfSecretary.findByMember(any())).thenReturn(Optional.of(Arrays.asList(new HistoryOfSecretary())));

        // Test getAllMember method
        Optional<List<HistoryOfSecretary>> result = hosService.getAllMember(1L);

        // Verify that the returned list is not empty
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void testGetAllDepartment() throws Exception {
        // Mocking dependencies
        when(repositoryDepartment.findById(anyLong())).thenReturn(Optional.of(new Department()));
        when(repositoryHistoryOfSecretary.findByDepartment(any())).thenReturn(Optional.of(Arrays.asList(new HistoryOfSecretary())));

        // Test getAllDepartment method
        Optional<List<HistoryOfSecretary>> result = hosService.getAllDepartment(1L);

        // Verify that the returned list is not empty
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void testUpdate() throws Exception {

        Long id = 1L;
        Department department = new Department(id,"Department1","Dept1");
        Member member = new Member(id,"Mihailo","Marcetic",new Academic_title(),new Education_title(),new Scientific_field(),department);
        HistoryOfSecretary hos = new HistoryOfSecretary(id, LocalDate.now(), LocalDate.now().plusDays(1),member,new Role(),department);

        // Mocking dependencies
        when(repositoryHistoryOfSecretary.findById(anyLong())).thenReturn(Optional.of(new HistoryOfSecretary()));
        when(repositoryMember.findById(anyLong())).thenReturn(Optional.of(new Member()));

        // Test update method
        hosService.update(id, hos);

        // Verify that repository save method is called once
        verify(repositoryHistoryOfSecretary, times(1)).save(any(HistoryOfSecretary.class));
    }

    @Test
    void testDelete() {
        // Mocking dependencies
        when(repositoryHistoryOfSecretary.findById(anyLong())).thenReturn(Optional.of(new HistoryOfSecretary()));

        // Test delete method
        String result = hosService.delete(1L);

        // Verify that the returned string is the expected message
        Assertions.assertEquals("You are successfully deleted history", result);
    }
}

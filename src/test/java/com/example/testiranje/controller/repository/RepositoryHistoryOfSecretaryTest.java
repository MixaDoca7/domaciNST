package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.*;
import com.example.testiranje.controller.repository.RepositoryHistoryOfSecretary;
import com.example.testiranje.controller.service.impl.HosRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RepositoryHistoryOfSecretaryTest {

    @Mock
    private RepositoryHistoryOfSecretary repository;
    @Mock
    Department department =new Department(1L,"Department1","Dept1");
    @Mock
    Member member = new Member(1L,"Mihailo","Marcetic",new Academic_title(),new Education_title(),new Scientific_field(),department);
    @Mock
    HistoryOfSecretary hos = new HistoryOfSecretary(1L, LocalDate.now(), LocalDate.now().plusDays(1),member,new Role(),department);


    @InjectMocks
    private HosRepositoryImpl service; // Replace SomeService with the class where you inject RepositoryHistoryOfSecretary


    @Test
    void testFindByMember() {
        List<HistoryOfSecretary> histories = new ArrayList<>();
        histories.add(hos);

        when(repository.findByMember(member)).thenReturn(Optional.of(histories));
        Optional<List<HistoryOfSecretary>> result = repository.findByMember(member);

        assertTrue(result.isPresent());
        assertEquals(histories, result.get());
    }

    @Test
    public void testFindTopByDepartmentOrderByEndtOfPositionDesc() {
        when(repository.findTopByDepartmentOrderByEndtOfPositionDesc(department)).thenReturn(Optional.of(hos));
        Optional<HistoryOfSecretary> result = repository.findTopByDepartmentOrderByEndtOfPositionDesc(department);

        assertTrue(result.isPresent());
        assertEquals(hos, result.get());
    }

    @Test
    public void testFindTopByDepartmentOrderByEndtOfPosition() {
        when(repository.findTopByDepartmentOrderByEndtOfPosition(department)).thenReturn(hos);
        HistoryOfSecretary result = repository.findTopByDepartmentOrderByEndtOfPosition(department);

        assertNotNull(result);
        assertEquals(hos, result);
    }

    @Test
    public void testFindByDepartment() {
        Department department2 = new Department(2L,"Department2","Dept2");

        List<HistoryOfSecretary> historyList = List.of(hos);
        when(repository.findByDepartment(department)).thenReturn(Optional.of(historyList));
        Optional<List<HistoryOfSecretary>> result1 = repository.findByDepartment(department);
        Optional<List<HistoryOfSecretary>> result2 = repository.findByDepartment(department2);

        assertTrue(result1.isPresent());
        assertEquals(historyList, result1.get());
        assertEquals(Optional.empty(),result2);
    }
}


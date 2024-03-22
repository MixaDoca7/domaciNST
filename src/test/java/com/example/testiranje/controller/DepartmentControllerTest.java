
package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.dto.DepartmentDto;
import com.example.testiranje.controller.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DepartmentControllerTest {

    @Mock
    DepartmentService departmentService;

    @InjectMocks
    DepartmentController departmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto();
        DepartmentDto savedDepartmentDto = new DepartmentDto();
        savedDepartmentDto.setId(1L);

        when(departmentService.save(any())).thenReturn(savedDepartmentDto);

        ResponseEntity<DepartmentDto> responseEntity = departmentController.save(departmentDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedDepartmentDto, responseEntity.getBody());
    }

    @Test
    void testGetAll() {
        List<Department> departmentList = new ArrayList<>();
        when(departmentService.getAll()).thenReturn(departmentList);

        ResponseEntity<List<Department>> responseEntity = departmentController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(departmentList, responseEntity.getBody());
    }

    @Test
    void testFindByIdFound() {
        Long id = 1L;
        Department department = new Department();
        when(departmentService.findById(id)).thenReturn(department);

        ResponseEntity<Department> responseEntity = departmentController.findById(id);

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(department, responseEntity.getBody());
    }

    @Test
    void testFindByIdNotFound() {
        Long id = 1L;
        when(departmentService.findById(id)).thenReturn(null);

        ResponseEntity<Department> responseEntity = departmentController.findById(id);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testDelete() {
        Long id = 1L;
        when(departmentService.delete(id)).thenReturn("Department deleted successfully");

        String result = departmentController.delete(id);

        assertEquals("Department deleted successfully", result);
    }

    @Test
    void testUpdate() throws Exception {
        Long id = 1L;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Updated Department");

        when(departmentService.update(id, departmentDto)).thenReturn(departmentDto);

        ResponseEntity<DepartmentDto> responseEntity = departmentController.update(id, departmentDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(departmentDto, responseEntity.getBody());
    }
}

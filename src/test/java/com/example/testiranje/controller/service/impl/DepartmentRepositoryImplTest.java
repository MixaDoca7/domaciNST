package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.converter.impl.DepartmentConverter;
import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.dto.DepartmentDto;
import com.example.testiranje.controller.repository.RepositoryDepartment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartmentRepositoryImplTest {

    @Mock
    private RepositoryDepartment repositoryDepartment;

    @Mock
    private DepartmentConverter departmentConverter;

    private DepartmentRepositoryImpl departmentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departmentRepository = new DepartmentRepositoryImpl(repositoryDepartment, departmentConverter);
    }

    @Test
    void save_NewDepartmentDto_SuccessfullySaved() throws Exception {
        // Arrange
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("New Department");
        departmentDto.setShort_name("ND");

        when(repositoryDepartment.findByName(anyString())).thenReturn(Optional.empty());
        when(departmentConverter.toEntity(departmentDto)).thenReturn(new Department());
        when(departmentConverter.toDto(any(Department.class))).thenReturn(departmentDto);

        // Act
        DepartmentDto savedDto = departmentRepository.save(departmentDto);

        // Assert
        assertNotNull(savedDto);
        assertEquals(departmentDto.getName(), savedDto.getName());
        assertEquals(departmentDto.getShort_name(), savedDto.getShort_name());
        verify(repositoryDepartment, times(1)).findByName(departmentDto.getName());
        verify(departmentConverter, times(1)).toEntity(departmentDto);
        verify(repositoryDepartment, times(1)).save(any(Department.class));
        verify(departmentConverter, times(1)).toDto(any(Department.class));
    }

    @Test
    void save_ExistingDepartmentDto_ExceptionThrown() {
        // Arrange
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Existing Department");

        when(repositoryDepartment.findByName(anyString())).thenReturn(Optional.of(new Department()));

        // Act & Assert
        assertThrows(Exception.class, () -> departmentRepository.save(departmentDto));
        verify(repositoryDepartment, times(1)).findByName(departmentDto.getName());
        verify(departmentConverter, never()).toEntity(departmentDto);
        verify(repositoryDepartment, never()).save(any(Department.class));
        verify(departmentConverter, never()).toDto(any(Department.class));
    }
    @Test
    public void testGetAll() {
        // Create a list of departments for testing
        List<Department> mockDepartments = new ArrayList<>();
        mockDepartments.add(new Department(1L, "Department1","Dept1"));
        mockDepartments.add(new Department(2L,"Department2", "Dept2"));

        // Mock the behavior of the repository method
        when(repositoryDepartment.findAll()).thenReturn(mockDepartments);

        // Call the method under test
        List<Department> result = departmentRepository.getAll();

        // Verify that the result matches the mocked data
        assertEquals(mockDepartments.size(), result.size());
        for (int i = 0; i < mockDepartments.size(); i++) {
            assertEquals(mockDepartments.get(i), result.get(i));
        }
        // Verify that findAll() method is called just once
        verify(repositoryDepartment, times(1)).findAll();
    }

    @Test
    public void testDelete() {
        // Mocking data
        Long id = 1L;
        Department department = new Department(id, "Department1","Dept1");

        // Mock the behavior of findById() method
        when(repositoryDepartment.findById(id)).thenReturn(Optional.of(department));

        // Call the method under test
        String result = departmentRepository.delete(id);

        // Verify that findById() and delete() methods are called with correct arguments and just once
        verify(repositoryDepartment, times(1)).findById(id);
        verify(repositoryDepartment,times(1)).delete(department);

        // Verify the result message
        assertEquals("You deleted department with id: " + id, result);
    }

    @Test
    public void testFindById_ExistingId() {
        // Mocking data
        Long id = 1L;
        Department department = new Department(id, "Department1","Dept1");

        // Mock the behavior of findById() method
        when(repositoryDepartment.findById(id)).thenReturn(Optional.of(department));

        // Call the method under test
        Department result = departmentRepository.findById(id);

        // Verify that findById() is called with correct argument
        // and the result is not null and matches the mocked department
        assertNotNull(result);
        assertEquals(department, result);

        //Verify that findById() is called only once and with right arguments
        verify(repositoryDepartment,times(1)).findById(id);
    }

    @Test
    public void testFindById_NonExistingId() {
        // Mocking data
        Long id = 1L;

        // Mock the behavior of findById() method
        when(repositoryDepartment.findById(id)).thenReturn(Optional.empty());

        // Call the method under test
        Department result = departmentRepository.findById(id);

        // Verify that findById() is called with correct argument
        // and the result is null since the department doesn't exist
        assertEquals(null, result);

        //Verify that findById() is called only once and with right arguments
        verify(repositoryDepartment,times(1)).findById(id);

    }

}

package com.example.testiranje;

import com.example.testiranje.controller.converter.impl.DepartmentConverter;
import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.dto.DepartmentDto;
import com.example.testiranje.controller.repository.RepositoryDepartment;
import com.example.testiranje.controller.service.impl.DepartmentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}

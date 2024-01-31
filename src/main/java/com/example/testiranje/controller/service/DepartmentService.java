package com.example.testiranje.controller.service;

import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto save(DepartmentDto departmentDto) throws Exception;
    List<Department> getAll();
    String delete(Long id);
    Department findById(Long id);
    DepartmentDto update(Long id, DepartmentDto departmentDto)throws Exception;

}

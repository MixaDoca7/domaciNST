package com.example.testiranje.controller.converter.impl;

import com.example.testiranje.controller.converter.DtoEntityConverter;
import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.dto.DepartmentDto;
import org.springframework.stereotype.Component;

@Component
public class DepartmentConverter implements DtoEntityConverter<DepartmentDto, Department> {

    @Override
    public DepartmentDto toDto(Department department) {
        return new DepartmentDto(department.getId(),department.getName(), department.getShort_name());
    }

    @Override
    public Department toEntity(DepartmentDto departmentDto) {
        return new Department(departmentDto.getId(),departmentDto.getName(), departmentDto.getShort_name());
    }
}

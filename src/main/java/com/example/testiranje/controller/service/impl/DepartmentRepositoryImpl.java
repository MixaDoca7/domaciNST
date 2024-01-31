package com.example.testiranje.controller.service.impl;


import com.example.testiranje.controller.converter.impl.DepartmentConverter;
import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.dto.DepartmentDto;
import com.example.testiranje.controller.repository.RepositoryDepartment;
import com.example.testiranje.controller.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "repositoryService")
public class DepartmentRepositoryImpl implements DepartmentService {

    private RepositoryDepartment repositoryDepartment;
    private DepartmentConverter departmentConverter;

    public DepartmentRepositoryImpl(RepositoryDepartment repositoryDepartment, DepartmentConverter departmentConverter) {
        this.repositoryDepartment = repositoryDepartment;
        this.departmentConverter = departmentConverter;
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) throws Exception {
        Optional<Department> dept = repositoryDepartment.findByName(departmentDto.getName());
        if(dept.isPresent()){
            throw new Exception("Ovaj department vec postoji");
        }else {
            Department department = departmentConverter.toEntity(departmentDto);
            repositoryDepartment.save(department);
            return departmentConverter.toDto(department);
        }
    }

    @Override
    public List<Department> getAll() {
        return repositoryDepartment.findAll();
    }

    @Override
    public String delete(Long id) {
        Department dept = findById(id);
        repositoryDepartment.delete(dept);
        return "You deleted department with id: "+ id;
    }

    @Override
    public Department findById(Long id) {
        Optional<Department> dept = repositoryDepartment.findById(id);
        if(dept.isPresent()){
            return dept.get();
        }
        return null;
    }

    @Override
    public DepartmentDto update(Long id, DepartmentDto departmentDto) throws Exception {
        Optional<Department> dept = repositoryDepartment.findById(id);
        if(dept.isPresent()){
            repositoryDepartment.save(departmentConverter.toEntity(departmentDto));
            return departmentDto;
        }
        throw new Exception("This department does not exist");
    }
}

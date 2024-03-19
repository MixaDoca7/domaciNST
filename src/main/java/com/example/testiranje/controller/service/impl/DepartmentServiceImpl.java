package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.converter.impl.DepartmentConverter;
import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.dto.DepartmentDto;
import com.example.testiranje.controller.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "memoryService")
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentConverter departmentConverter;

    List<Department> departments;

    public DepartmentServiceImpl(DepartmentConverter departmentConverter) {
        this.departmentConverter = departmentConverter;
        departments = new ArrayList<>();
        departments.add(new Department(1l,"Dept1","d1"));
        departments.add(new Department(2l,"Dept2","d2"));
        departments.add(new Department(3l,"Dept3", "d3"));
        departments.add(new Department(4l,"Dept4","d4"));
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        return departmentDto;
    }

    @Override
    public List<Department> getAll() {
        return departments;
    }

    @Override
    public String delete(Long id) {
        for (Department d : departments){
            if(d.getId().equals(id)) {
                departments.remove(d);
                return "Removed department with id: " + id;
            }
        }
        return "Index does not exist";
    }

    @Override
    public Department findById(Long id) {
        for(Department d : departments){
            if(d.getId().equals(id))
                return d;
        }
        return null;
    }

    @Override
    public DepartmentDto update(Long id, DepartmentDto departmentDto) throws Exception {
        return null;
    }
}

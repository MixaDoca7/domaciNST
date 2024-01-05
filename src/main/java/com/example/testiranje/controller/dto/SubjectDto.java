package com.example.testiranje.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class SubjectDto implements Serializable {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 10)
    private String name;

    private int espb;

    private DepartmentDto departmentDto;

    public SubjectDto() {
    }

    public SubjectDto(Long id, String name, int espb, DepartmentDto departmentDto) {
        this.id = id;
        this.name = name;
        this.espb = espb;
        this.departmentDto = departmentDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }
}

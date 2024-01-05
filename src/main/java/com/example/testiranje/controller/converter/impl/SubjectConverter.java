package com.example.testiranje.controller.converter.impl;

import com.example.testiranje.controller.converter.DtoEntityConverter;
import com.example.testiranje.controller.domane.Subject;
import com.example.testiranje.controller.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter implements DtoEntityConverter<SubjectDto, Subject> {

    @Autowired
    private  DepartmentConverter departmentConverter;

    @Override
    public SubjectDto toDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getName(),
                subject.getEspb(), departmentConverter.toDto(subject.getDepartment()));
    }

    @Override
    public Subject toEntity(SubjectDto subjectDto) {
        return new Subject(subjectDto.getId(),subjectDto.getName(),
                subjectDto.getEspb(),departmentConverter.toEntity(subjectDto.getDepartmentDto()));
    }
}

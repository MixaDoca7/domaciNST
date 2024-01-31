package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.converter.impl.SubjectConverter;
import com.example.testiranje.controller.domane.Department;
import com.example.testiranje.controller.domane.Subject;
import com.example.testiranje.controller.dto.DepartmentDto;
import com.example.testiranje.controller.dto.SubjectDto;
import com.example.testiranje.controller.repository.RepositoryDepartment;
import com.example.testiranje.controller.repository.RepositorySubject;
import com.example.testiranje.controller.service.SubjectService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectRepositoryImpl implements SubjectService {

    private SubjectConverter subjectConverter;
    private RepositorySubject repositorySubject;
    private RepositoryDepartment repositoryDepartment;

    public SubjectRepositoryImpl(SubjectConverter subjectConverter, RepositorySubject repositorySubject, RepositoryDepartment repositoryDepartment) {
        this.subjectConverter = subjectConverter;
        this.repositorySubject = repositorySubject;
        this.repositoryDepartment = repositoryDepartment;
    }

    @Override
    public SubjectDto save(SubjectDto subjectDto) throws Exception {
        Subject subject = subjectConverter.toEntity(subjectDto);
        if(repositorySubject.findByName(subject.getName()).isEmpty()){
            if (repositoryDepartment.findByName(subject.getDepartment().getName()).isEmpty()){
                repositoryDepartment.save(subject.getDepartment());
            }
            repositorySubject.save(subject);
            return subjectDto;
        }
        throw new Exception("Subject already exist");
    }

    @Override
    public List<SubjectDto> getAll() {
        return repositorySubject
                .findAll()
                .stream().map(entity -> subjectConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Subject> subject = repositorySubject.findById(id);
        if(subject.isPresent()){
            Subject sub = subject.get();
            repositorySubject.delete(sub);
        }else{
            throw new Exception("Subject do not exist");
        }

    }

    @Override
    public SubjectDto findById(Long id) throws Exception {
        Optional<Subject> subject = repositorySubject.findById(id);
        if(subject.isPresent()){
            return subjectConverter.toDto(subject.get());
        }
        else{
            throw new Exception("Subject do not exist");
        }
    }
    @Override
    public SubjectDto update(Long id, SubjectDto subjectDto) throws Exception {
        Optional<Subject> sub = repositorySubject.findById(id);
        if(sub.isPresent()){
            repositorySubject.save(subjectConverter.toEntity(subjectDto));
            return subjectDto;
        }
        throw new Exception("This subject does not exist");
    }
}

package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.Academic_title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.Optional;

public interface RepositoryAcademicTitle extends JpaRepository<Academic_title, Long> {

    Optional<Academic_title> findByName(String academic_title);

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    <S extends Academic_title> S save(S entity);

}

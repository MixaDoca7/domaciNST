package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorySubject extends JpaRepository<Subject,Long> {

    Optional<Subject> findByName(String name);
}

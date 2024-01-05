package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryDepartment extends JpaRepository<Department, Long>{

    Optional<Department> findByName(String name);
}

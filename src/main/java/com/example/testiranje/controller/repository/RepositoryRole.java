package com.example.testiranje.controller.repository;

import com.example.testiranje.controller.domane.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRole extends JpaRepository<Role, Long> {
    Role findByName(String name);

}

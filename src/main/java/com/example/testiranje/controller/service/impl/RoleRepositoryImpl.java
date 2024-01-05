package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.domane.Role;
import com.example.testiranje.controller.repository.RepositoryRole;
import com.example.testiranje.controller.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleRepositoryImpl implements RoleService {

    private final RepositoryRole repositoryRole;

    public RoleRepositoryImpl(RepositoryRole repositoryRole) {
        this.repositoryRole = repositoryRole;
    }

    @Override
    public void save(Role role) {
        repositoryRole.save(role);
    }
}

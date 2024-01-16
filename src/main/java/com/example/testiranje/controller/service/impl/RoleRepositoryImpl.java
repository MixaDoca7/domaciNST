package com.example.testiranje.controller.service.impl;

import com.example.testiranje.controller.domane.Role;
import com.example.testiranje.controller.repository.RepositoryRole;
import com.example.testiranje.controller.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Role delete(Long id) throws Exception {
        Optional<Role> r = repositoryRole.findById(id);
        if(r.isPresent()){
            return r.get();
        }
        throw new Exception("Role doesnt exist");
    }

    @Override
    public List<Role> getAll() {
        return repositoryRole.findAll();
    }
}

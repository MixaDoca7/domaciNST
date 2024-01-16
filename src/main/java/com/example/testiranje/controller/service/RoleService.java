package com.example.testiranje.controller.service;


import com.example.testiranje.controller.domane.Role;

import java.util.List;

public interface RoleService {

    void save(Role role);

    Role delete(Long id) throws Exception;

    List<Role> getAll();
}

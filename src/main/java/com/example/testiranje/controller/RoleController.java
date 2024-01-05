package com.example.testiranje.controller;

import com.example.testiranje.controller.domane.Role;
import com.example.testiranje.controller.service.impl.RoleRepositoryImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleRepositoryImpl roleRepository;

    public RoleController(RoleRepositoryImpl roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping
    public void save(@RequestBody Role role){
        roleRepository.save(role);
    }
}

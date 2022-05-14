package com.example.api.service;

import com.example.api.model.Role;
import com.example.api.repository.RoleRepo;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepo roleRepository;

    public RoleService(RoleRepo roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}

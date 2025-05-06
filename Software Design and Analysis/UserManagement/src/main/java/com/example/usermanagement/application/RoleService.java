package com.example.usermanagement.application;

import com.example.usermanagement.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;

import java.util.UUID;

public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UUID createRole(String roleName) {
        if (roleName == null || roleName.trim().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be blank");
        }
        Role role = new Role(roleName);
        return roleRepository.save(role).getId();
    }

}
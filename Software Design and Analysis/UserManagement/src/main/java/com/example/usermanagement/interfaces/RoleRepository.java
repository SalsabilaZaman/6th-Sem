package com.example.usermanagement.interfaces;

import com.example.usermanagement.domain.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository {
    Role save(Role role);
    Optional findById(UUID id);
}
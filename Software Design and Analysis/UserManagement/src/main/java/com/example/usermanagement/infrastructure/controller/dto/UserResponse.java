package com.example.usermanagement.infrastructure.controller.dto;

import java.util.List; import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private List roles;

    public UserResponse(UUID id, String name, String email, List<RoleResponse> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<RoleResponse> getRoles() {
        return roles;
    }

}
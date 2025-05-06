package com.example.usermanagement.infrastructure.controller.dto;
import jakarta.validation.constraints.NotBlank;

public class RoleRequest {
    @NotBlank(message = "Role name cannot be blank")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
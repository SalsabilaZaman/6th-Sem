package com.example.usermanagement.infrastructure.controller;

import com.example.usermanagement.application.RoleService;
import com.example.usermanagement.infrastructure.controller.dto.RoleRequest;
import jakarta.validation.Valid; import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController @RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<UUID> createRole(@Valid @RequestBody RoleRequest request) {
        UUID roleId = roleService.createRole(request.getRoleName());
        return ResponseEntity.status(HttpStatus.CREATED).body(roleId);
    }

}
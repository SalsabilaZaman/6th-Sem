package com.example.usermanagement.infrastructure.controller;

import com.example.usermanagement.application.UserService;
import com.example.usermanagement.domain.User;
import com.example.usermanagement.infrastructure.controller.dto.UserRequest;
import com.example.usermanagement.infrastructure.controller.dto.UserResponse;
import com.example.usermanagement.infrastructure.controller.dto.RoleResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController @RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UUID> createUser(@Valid @RequestBody UserRequest request) {
        UUID userId = userService.createUser(request.getName(), request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

    @PostMapping("/{userId}/assign-role/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable UUID userId, @PathVariable UUID roleId) {
        userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok("Role assigned successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable UUID id) {
        return userService.getUserDetails(id)
                .map(user -> ResponseEntity.ok(toResponse(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private UserResponse toResponse(User user) {
        List<RoleResponse> roleResponses = user.getRoles().stream()
                .map(role -> new RoleResponse(role.getId(), role.getRoleName()))
                .collect(Collectors.toList());
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), roleResponses);
    }

}
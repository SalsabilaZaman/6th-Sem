package com.example.usermanagement.application;

import com.example.usermanagement.interfaces.UserRepository;
import com.example.usermanagement.interfaces.RoleRepository;
import com.example.usermanagement.domain.User;
import com.example.usermanagement.domain.Role;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UUID createUser(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        User user = new User(name, email);
        return userRepository.save(user).getId();
    }

    public void assignRoleToUser(UUID userId, UUID roleId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Role> roleOpt = roleRepository.findById(roleId);

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        if (roleOpt.isEmpty()) {
            throw new IllegalArgumentException("Role not found");
        }

        User user = userOpt.get();
        user.assignRole(roleOpt.get());
        userRepository.save(user);
    }

    public Optional<User> getUserDetails(UUID id) {
        return userRepository.findById(id);
    }

}


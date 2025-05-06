package com.example.usermanagement.interfaces;

import com.example.usermanagement.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional findById(UUID id);
}
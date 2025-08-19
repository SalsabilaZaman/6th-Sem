package com.example.usermanagement.application;

import static org.junit.jupiter.api.Assertions.*;

import com.example.usermanagement.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoleServiceTest {
    @Mock private
    RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRole_validInput_returnsRoleId() {
        String roleName = "ADMIN";
        Role role = new Role(roleName);
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        UUID roleId = roleService.createRole(roleName);

        assertNotNull(roleId);
        verify(roleRepository).save(any(Role.class));
    }

    @Test
    void createRole_blankName_throwsException() {
        assertThrows(IllegalArgumentException.class, () ->
                roleService.createRole(""));
    }

}
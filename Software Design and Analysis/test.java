java package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.RoleRepository; import com.example.usermanagement.application.interfaces.UserRepository; import com.example.usermanagement.domain.Role; import com.example.usermanagement.domain.User; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.MockitoAnnotations;

import java.util.Optional; import java.util.UUID;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.ArgumentMatchers.any; import static org.mockito.Mockito.;

class UserServiceTest { @Mock private UserRepository userRepository;

@Mock
private RoleRepository roleRepository;

@InjectMocks
private UserService userService;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
}

@Test
void createUser_validInput_returnsUserId() {
    String name = "John Doe";
    String email = "john@example.com";
    User user = new User(name, email);
    when(userRepository.save(any(User.class))).thenReturn(user);

    UUID userId = userService.createUser(name, email);

    assertNotNull(userId);
    verify(userRepository).save(any(User.class));
}

@Test
void createUser_invalidEmail_throwsException() {
    assertThrows(IllegalArgumentException.class, () -> 
        userService.createUser("John Doe", "invalid-email"));
}

@Test
void assignRoleToUser_validIds_assignsRole() {
    UUID userId = UUID.randomUUID();
    UUID roleId = UUID.randomUUID();
    User user = new User("John Doe", "john@example.com");
    Role role = new Role("ADMIN");
    
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
    when(userRepository.save(any(User.class))).thenReturn(user);

    userService.assignRoleToUser(userId, roleId);

    verify(userRepository).save(any(User.class));
}

@Test
void assignRoleToUser_userNotFound_throwsException() {
    UUID userId = UUID.randomUUID();
    UUID roleId = UUID.randomUUID();
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> 
        userService.assignRoleToUser(userId, roleId));
}

@Test
void getUserDetails_existingUser_returnsUser() {
    UUID userId = UUID.randomUUID();
    User user = new User("John Doe", "john@example.com");
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    Optional<User> result = userService.getUserDetails(userId);

    assertTrue(result.isPresent());
    assertEquals(user, result.get());
}

}

usermanagement/src/test/java/com/example/usermanagement/application/RoleServiceTest.java package com.example.usermanagement.application;

import com.example.usermanagement.application.interfaces.RoleRepository; import com.example.usermanagement.domain.Role; import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.; import static org.mockito.ArgumentMatchers.any; import static org.mockito.Mockito.;

class RoleServiceTest { @Mock private RoleRepository roleRepository;

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

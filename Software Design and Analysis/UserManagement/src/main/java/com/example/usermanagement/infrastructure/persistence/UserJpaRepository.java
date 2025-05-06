package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.interfaces.UserRepository;
import com.example.usermanagement.domain.User;
import com.example.usermanagement.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserJpaRepository implements UserRepository {
    private final UserJpaEntityRepository jpaRepository;

    public UserJpaRepository(UserJpaEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = toJpaEntity(user);
        UserJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private UserJpaEntity toJpaEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setRoles(user.getRoles().stream()
                .map(this::toRoleJpaEntity)
                .collect(Collectors.toList()));
        return entity;
    }

    private RoleJpaEntity toRoleJpaEntity(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        return entity;
    }

    private User toDomain(UserJpaEntity entity) {
        User user = new User(entity.getName(), entity.getEmail());
        try {
            java.lang.reflect.Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(user, entity.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set user ID", e);
        }
        List<Role> roles = entity.getRoles().stream()
                .map(r -> {
                    Role role = new Role(r.getRoleName());
                    try {
                        java.lang.reflect.Field idField = Role.class.getDeclaredField("id");
                        idField.setAccessible(true);
                        idField.set(role, r.getId());
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException("Failed to set role ID", e);
                    }
                    return role;
                })
                .collect(Collectors.toList());
        roles.forEach(user::assignRole);
        return user;
    }

}
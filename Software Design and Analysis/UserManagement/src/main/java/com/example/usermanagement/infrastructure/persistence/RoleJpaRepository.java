package com.example.usermanagement.infrastructure.persistence;

import com.example.usermanagement.interfaces.RoleRepository;
import com.example.usermanagement.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional; import java.util.UUID;

@Repository public class RoleJpaRepository implements RoleRepository {
    private final RoleJpaEntityRepository jpaRepository;

    public RoleJpaRepository(RoleJpaEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Role save(Role role) {
        RoleJpaEntity entity = toJpaEntity(role);
        RoleJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private RoleJpaEntity toJpaEntity(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getRoleName());
        return entity;
    }

    private Role toDomain(RoleJpaEntity entity) {
        Role role = new Role(entity.getRoleName());
        try {
            java.lang.reflect.Field idField = Role.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(role, entity.getId());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set role ID", e);
        }
        return role;
    }

}
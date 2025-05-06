package com.example.usermanagement.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleJpaEntityRepository extends JpaRepository<RoleJpaEntity, UUID> {

}
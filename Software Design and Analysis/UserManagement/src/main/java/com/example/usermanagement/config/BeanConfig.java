package com.example.usermanagement.config;

import com.example.usermanagement.application.RoleService;
import com.example.usermanagement.application.UserService;
import com.example.usermanagement.infrastructure.persistence.RoleJpaEntityRepository;
import com.example.usermanagement.interfaces.RoleRepository;
import com.example.usermanagement.interfaces.UserRepository;
import com.example.usermanagement.infrastructure.persistence.RoleJpaRepository;
import com.example.usermanagement.infrastructure.persistence.UserJpaEntityRepository;
import com.example.usermanagement.infrastructure.persistence.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration public class BeanConfig { @Bean public UserService userService(UserRepository userRepository, RoleRepository roleRepository) { return new UserService(userRepository, roleRepository); }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleService(roleRepository);
    }

    @Bean
    public UserRepository userRepository(UserJpaEntityRepository jpaRepository) {
        return new UserJpaRepository(jpaRepository);
    }

    @Bean
    public RoleRepository roleRepository(RoleJpaEntityRepository jpaRepository) {
        return new RoleJpaRepository(jpaRepository);
    }

}
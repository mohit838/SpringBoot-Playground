package com.mohitul.blog_apps_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mohitul.blog_apps_demo.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}

package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;

@RepositoryRestController
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {
    Optional<AdminUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
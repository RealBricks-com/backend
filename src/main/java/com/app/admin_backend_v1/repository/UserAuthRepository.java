package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
}
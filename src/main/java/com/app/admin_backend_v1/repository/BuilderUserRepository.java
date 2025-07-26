package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.BuilderUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuilderUserRepository extends JpaRepository<BuilderUser, Integer> {
}
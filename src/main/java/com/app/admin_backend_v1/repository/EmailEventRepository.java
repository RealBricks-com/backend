package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.EmailEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailEventRepository extends JpaRepository<EmailEvent, Integer> {
}
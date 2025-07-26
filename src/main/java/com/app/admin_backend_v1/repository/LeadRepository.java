package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Integer> {
}
package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Integer> {
}
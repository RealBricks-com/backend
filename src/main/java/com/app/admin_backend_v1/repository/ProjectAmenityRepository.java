package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.ProjectAmenity;
import com.app.admin_backend_v1.entity.ProjectAmenityId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectAmenityRepository extends JpaRepository<ProjectAmenity, ProjectAmenityId> {
}
package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface AreaRepository extends JpaRepository<Area, Integer> {
}
package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface AmenityRepository extends JpaRepository<Amenity, Integer> {
}
package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository<City, Integer> {
}
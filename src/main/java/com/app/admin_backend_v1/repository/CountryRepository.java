package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
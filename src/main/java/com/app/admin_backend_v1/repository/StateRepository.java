package com.app.admin_backend_v1.repository;

import com.app.admin_backend_v1.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
}
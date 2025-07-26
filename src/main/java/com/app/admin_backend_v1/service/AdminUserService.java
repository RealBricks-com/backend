// --- SERVICE ---
package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.*;
import com.app.admin_backend_v1.entity.AdminUser;
import com.app.admin_backend_v1.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserResponseDTO createAdmin(AdminUserCreateRequestDTO dto) {
        AdminUser user = new AdminUser();
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPasswordHash()));
        user.setFirstName(dto.getFirstName());
        user.setRole(dto.getRole() != null ? dto.getRole() : "super_admin");
        user.setCreatedAt(Instant.now());
        user = adminUserRepository.save(user);

        return toResponseDTO(user);
    }

    public AdminUserResponseDTO login(AdminUserLoginRequestDTO dto) {
        AdminUser user = adminUserRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }
        return toResponseDTO(user);
    }

    public List<AdminUserResponseDTO> getAll() {
        return adminUserRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AdminUserResponseDTO getById(Integer id) {
        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toResponseDTO(user);
    }

    public AdminUserResponseDTO update(Integer id, AdminUserCreateRequestDTO dto) {
        AdminUser user = adminUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setRole(dto.getRole());
        return toResponseDTO(adminUserRepository.save(user));
    }

    public void delete(Integer id) {
        adminUserRepository.deleteById(id);
    }

    private AdminUserResponseDTO toResponseDTO(AdminUser user) {
        AdminUserResponseDTO dto = new AdminUserResponseDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
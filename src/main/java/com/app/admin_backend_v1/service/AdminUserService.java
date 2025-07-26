// AdminUserService.java
package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.AdminUserDTO;
import com.app.admin_backend_v1.dto.LoginRequest;
import com.app.admin_backend_v1.dto.LoginResponse;
import com.app.admin_backend_v1.entity.AdminUser;
import com.app.admin_backend_v1.repository.AdminUserRepository;
import com.app.admin_backend_v1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminUserRepository = adminUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Sign up (Create new admin user)
    @Transactional
    public AdminUserDTO signUp(AdminUserDTO adminUserDTO) {
        if (adminUserRepository.existsByEmail(adminUserDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        AdminUser adminUser = new AdminUser();
        adminUser.setEmail(adminUserDTO.getEmail());
        adminUser.setPasswordHash(passwordEncoder.encode(adminUserDTO.getPassword()));
        adminUser.setFirstName(adminUserDTO.getFirstName());
        adminUser.setRole(adminUserDTO.getRole() != null ? adminUserDTO.getRole() : "super_admin");
        adminUser.setCreatedAt(Instant.now());

        AdminUser savedUser = adminUserRepository.save(adminUser);
        return convertToDTO(savedUser);
    }

    // Sign in
    public LoginResponse signIn(LoginRequest loginRequest) {
        AdminUser adminUser = adminUserRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), adminUser.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = JwtUtil.generateToken(adminUser.getEmail());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUser(convertToDTO(adminUser));
        return response;
    }

    // Check if user exists
    public boolean checkUserExists(String email) {
        return adminUserRepository.existsByEmail(email);
    }

    // Get admin user by ID
    public AdminUserDTO getAdminUserById(Integer id) {
        AdminUser adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));
        return convertToDTO(adminUser);
    }

    // Get admin user by email
    public AdminUserDTO getAdminUserByEmail(String email) {
        AdminUser adminUser = adminUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));
        return convertToDTO(adminUser);
    }

    // Get all admin users
    public List<AdminUserDTO> getAllAdminUsers() {
        return adminUserRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update admin user
    @Transactional
    public AdminUserDTO updateAdminUser(Integer id, AdminUserDTO adminUserDTO) {
        AdminUser adminUser = adminUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin user not found"));

        if (adminUserDTO.getEmail() != null && !adminUser.getEmail().equals(adminUserDTO.getEmail())) {
            if (adminUserRepository.existsByEmail(adminUserDTO.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            adminUser.setEmail(adminUserDTO.getEmail());
        }

        if (adminUserDTO.getPassword() != null) {
            adminUser.setPasswordHash(passwordEncoder.encode(adminUserDTO.getPassword()));
        }
        if (adminUserDTO.getFirstName() != null) {
            adminUser.setFirstName(adminUserDTO.getFirstName());
        }
        if (adminUserDTO.getRole() != null) {
            adminUser.setRole(adminUserDTO.getRole());
        }

        AdminUser updatedUser = adminUserRepository.save(adminUser);
        return convertToDTO(updatedUser);
    }

    // Delete admin user
    @Transactional
    public void deleteAdminUser(Integer id) {
        if (!adminUserRepository.existsById(id)) {
            throw new RuntimeException("Admin user not found");
        }
        adminUserRepository.deleteById(id);
    }

    // Convert Entity to DTO
    private AdminUserDTO convertToDTO(AdminUser adminUser) {
        AdminUserDTO dto = new AdminUserDTO();
        dto.setId(adminUser.getId());
        dto.setEmail(adminUser.getEmail());
        dto.setFirstName(adminUser.getFirstName());
        dto.setRole(adminUser.getRole());
        dto.setCreatedAt(adminUser.getCreatedAt());
        return dto;
    }
}
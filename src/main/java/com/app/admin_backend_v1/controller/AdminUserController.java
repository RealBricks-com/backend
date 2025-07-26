// AdminUserController.java
package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.AdminUserDTO;
import com.app.admin_backend_v1.dto.LoginRequest;
import com.app.admin_backend_v1.dto.LoginResponse;
import com.app.admin_backend_v1.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    // Sign up
    @PostMapping("/signup")
    public ResponseEntity<AdminUserDTO> signUp(@RequestBody AdminUserDTO adminUserDTO) {
        AdminUserDTO createdUser = adminUserService.signUp(adminUserDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Sign in
    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> signIn(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = adminUserService.signIn(loginRequest);
        return ResponseEntity.ok(response);
    }

    // Check if user exists
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable String email) {
        boolean exists = adminUserService.checkUserExists(email);
        return ResponseEntity.ok(exists);
    }

    // Get admin user by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminUserDTO> getAdminUserById(@PathVariable Integer id) {
        AdminUserDTO adminUser = adminUserService.getAdminUserById(id);
        return ResponseEntity.ok(adminUser);
    }

    // Get admin user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<AdminUserDTO> getAdminUserByEmail(@PathVariable String email) {
        AdminUserDTO adminUser = adminUserService.getAdminUserByEmail(email);
        return ResponseEntity.ok(adminUser);
    }

    // Get all admin users
    @GetMapping
    public ResponseEntity<List<AdminUserDTO>> getAllAdminUsers() {
        List<AdminUserDTO> adminUsers = adminUserService.getAllAdminUsers();
        return ResponseEntity.ok(adminUsers);
    }

    // Update admin user
    @PutMapping("/{id}")
    public ResponseEntity<AdminUserDTO> updateAdminUser(@PathVariable Integer id,
                                                        @RequestBody AdminUserDTO adminUserDTO) {
        AdminUserDTO updatedUser = adminUserService.updateAdminUser(id, adminUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete admin user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminUser(@PathVariable Integer id) {
        adminUserService.deleteAdminUser(id);
        return ResponseEntity.noContent().build();
    }
}

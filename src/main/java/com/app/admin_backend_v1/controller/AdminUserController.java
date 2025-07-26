// --- CONTROLLER ---
package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.*;
import com.app.admin_backend_v1.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-users")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping
    public AdminUserResponseDTO create(@RequestBody AdminUserCreateRequestDTO dto) {
        return adminUserService.createAdmin(dto);
    }

    @PostMapping("/login")
    public AdminUserResponseDTO login(@RequestBody AdminUserLoginRequestDTO dto) {
        return adminUserService.login(dto);
    }

    @GetMapping
    public List<AdminUserResponseDTO> getAll() {
        return adminUserService.getAll();
    }

    @GetMapping("/{id}")
    public AdminUserResponseDTO getById(@PathVariable Integer id) {
        return adminUserService.getById(id);
    }

    @PutMapping("/{id}")
    public AdminUserResponseDTO update(@PathVariable Integer id, @RequestBody AdminUserCreateRequestDTO dto) {
        return adminUserService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        adminUserService.delete(id);
    }
}
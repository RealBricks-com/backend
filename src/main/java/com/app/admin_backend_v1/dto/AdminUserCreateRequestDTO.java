package com.app.admin_backend_v1.dto;

import lombok.Data;

@Data
public class AdminUserCreateRequestDTO {
    private String email;
    private String passwordHash;
    private String firstName;
    private String role; // Optional, defaults to "super_admin"
}

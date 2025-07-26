package com.app.admin_backend_v1.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class AdminUserResponseDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String role;
    private Instant createdAt;
}

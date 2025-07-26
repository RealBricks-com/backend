package com.app.admin_backend_v1.dto;

import lombok.Data;

@Data
public class AdminUserLoginRequestDTO {
    private String email;
    private String password;
}

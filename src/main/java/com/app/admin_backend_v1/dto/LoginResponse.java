// LoginResponse.java (New DTO for login response)
package com.app.admin_backend_v1.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private AdminUserDTO user;
}
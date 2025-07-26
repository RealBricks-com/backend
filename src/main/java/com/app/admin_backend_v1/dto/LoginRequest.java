// LoginRequest.java (New DTO for sign-in)
package com.app.admin_backend_v1.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
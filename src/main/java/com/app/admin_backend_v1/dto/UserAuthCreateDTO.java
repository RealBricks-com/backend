package com.app.admin_backend_v1.dto;

import lombok.Data;

@Data
public class UserAuthCreateDTO {
    private String email;
    private String googleId;
    private String firstName;
    private String phone;
}
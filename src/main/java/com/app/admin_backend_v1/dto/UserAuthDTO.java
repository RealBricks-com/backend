package com.app.admin_backend_v1.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserAuthDTO {
    private Integer id;
    private String email;
    private String googleId;
    private String firstName;
    private String phone;
    private Instant createdAt;
}
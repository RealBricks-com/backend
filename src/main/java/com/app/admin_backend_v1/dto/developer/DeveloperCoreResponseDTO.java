package com.app.admin_backend_v1.dto.developer;

import lombok.Data;

import java.time.Instant;

@Data
public class DeveloperCoreResponseDTO {
    private Integer id;
    private String reraId;
    private String name;
    private String email;
    private String phone;
    private Instant createdAt;
}

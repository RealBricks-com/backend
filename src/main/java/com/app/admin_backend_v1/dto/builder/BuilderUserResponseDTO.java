// BuilderUserResponseDTO.java
package com.app.admin_backend_v1.dto.builder;

import lombok.Data;

import java.time.Instant;

@Data
public class BuilderUserResponseDTO {
    private Integer id;
    private Integer developerId;
    private String reraId;
    private String name;
    private String email;
    private Instant createdAt;
}

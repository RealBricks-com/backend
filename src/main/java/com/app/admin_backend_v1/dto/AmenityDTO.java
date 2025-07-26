package com.app.admin_backend_v1.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class AmenityDTO {
    private Integer id;
    private String name;
    private String category;
    private Instant createdAt;
}

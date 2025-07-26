package com.app.admin_backend_v1.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class ProjectCoreDTO {
    private Integer id;
    private String reraId;
    private String name;
    private String slug;
    private String description;
    private Integer developerId;
    private Integer areaId;
    private String propertyType;
    private Integer carpetAreaSqft;
    private BigDecimal minPrice;
    private String status;
    private Instant createdAt;
}
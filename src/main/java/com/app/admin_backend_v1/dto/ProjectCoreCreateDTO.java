package com.app.admin_backend_v1.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProjectCoreCreateDTO {
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
}
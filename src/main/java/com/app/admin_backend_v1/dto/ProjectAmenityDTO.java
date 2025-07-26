package com.app.admin_backend_v1.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ProjectAmenityDTO {
    private Integer projectId;
    private Integer amenityId;
    private Instant createdAt;
}
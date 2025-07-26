package com.app.admin_backend_v1.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class ProjectMediaDTO {
    private Integer id;
    private Integer projectId;
    private String fileUrl;
    private String mediaType;
    private Instant createdAt;
}
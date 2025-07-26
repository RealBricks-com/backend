package com.app.admin_backend_v1.dto;

import lombok.Data;

@Data
public class ProjectMediaCreateDTO {
    private Integer projectId;
    private String fileUrl;
    private String mediaType;
}
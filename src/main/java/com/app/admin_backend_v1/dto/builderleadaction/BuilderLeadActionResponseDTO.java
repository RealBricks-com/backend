package com.app.admin_backend_v1.dto.builderleadaction;

import lombok.Data;

import java.time.Instant;

@Data
public class BuilderLeadActionResponseDTO {
    private Integer id;
    private Integer leadId;
    private Integer developerId;
    private String actionType;
    private String notes;
    private Instant createdAt;
}

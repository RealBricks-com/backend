package com.app.admin_backend_v1.dto.builderleadaction;

import lombok.Data;

@Data
public class BuilderLeadActionUpdateDTO {
    private Integer developerId;
    private String actionType;
    private String notes;
}

package com.app.admin_backend_v1.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class LeadDTO {
    private Integer id;
    private Integer projectId;
    private Integer developerId;
    private Integer userId;
    private String name;
    private String email;
    private String phone;
    private BigDecimal budgetMin;
    private BigDecimal budgetMax;
    private String leadStatus;
    private Integer leadScore;
    private Instant createdAt;
}
package com.app.admin_backend_v1.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class StateDTO {
    private Integer id;
    private String name;
    private Integer countryId;
    private Instant createdAt;
}

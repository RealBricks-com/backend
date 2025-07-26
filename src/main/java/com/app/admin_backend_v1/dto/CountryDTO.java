package com.app.admin_backend_v1.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class CountryDTO {
    private Integer id;
    private String name;
    private Instant createdAt;
}

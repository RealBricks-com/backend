package com.app.admin_backend_v1.dto.city;

import lombok.Data;
import java.time.Instant;

@Data
public class CityResponseDTO {
    private Integer id;
    private String name;
    private Integer stateId;
    private String stateName;
    private Instant createdAt;
}

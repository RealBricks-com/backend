package com.app.admin_backend_v1.dto;

import lombok.Data;

@Data
public class ProjectSpecificationDTO {
    private Integer id;
    private Integer projectId;
    private String specCategory;
    private String specName;
    private String specValue;
    private String brandName;
    private String description;
    private Integer displayOrder;
}
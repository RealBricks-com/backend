// BuilderUserRequestDTO.java
package com.app.admin_backend_v1.dto.builder;

import lombok.Data;

@Data
public class BuilderUserRequestDTO {
    private Integer developerId;
    private String reraId;
    private String password; // plain password
    private String name;
    private String email;
}

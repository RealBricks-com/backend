package com.app.admin_backend_v1.dto.email_event;

import lombok.Data;

@Data
public class EmailEventCreateRequestDTO {
    private Integer leadId;
    private String emailType;
    private String recipientEmail;
    private String subject;
    private String body;
}

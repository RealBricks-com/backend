package com.app.admin_backend_v1.dto.email_event;

import lombok.Data;

import java.time.Instant;

@Data
public class EmailEventResponseDTO  {
    private Integer id;
    private String emailType;
    private String recipientEmail;
    private String subject;
    private String body;
    private String status;
    private Instant createdAt;
}

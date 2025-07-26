package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.email_event.EmailEventCreateRequestDTO;
import com.app.admin_backend_v1.dto.email_event.EmailEventResponseDTO;
import com.app.admin_backend_v1.service.EmailEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email-events")
public class EmailEventController {

    @Autowired
    private EmailEventService emailEventService;

    @PostMapping
    public EmailEventResponseDTO createEmailEvent(@RequestBody EmailEventCreateRequestDTO dto) {
        return emailEventService.createEmailEvent(dto);
    }
}

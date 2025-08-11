package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.email_event.EmailEventCreateRequestDTO;
import com.app.admin_backend_v1.dto.email_event.EmailEventResponseDTO;
import com.app.admin_backend_v1.service.EmailEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/email-events")
public class EmailEventController {

    private final EmailEventService emailEventService;

    public EmailEventController(EmailEventService emailEventService) {
        this.emailEventService = emailEventService;
    }

    // GET all email events
    @GetMapping
    public ResponseEntity<List<EmailEventResponseDTO>> getAllEmailEvents() {
        return ResponseEntity.ok(emailEventService.getAllEmailEvents());
    }

    // GET email event by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmailEventResponseDTO> getEmailEventById(@PathVariable Integer id) {
        return ResponseEntity.ok(emailEventService.getEmailEventById(id));
    }

    // CREATE email event
    @PostMapping
    public ResponseEntity<EmailEventResponseDTO> createEmailEvent(@RequestBody EmailEventCreateRequestDTO dto) {
        return ResponseEntity.ok(emailEventService.createEmailEvent(dto));
    }

    // UPDATE email event
    @PutMapping("/{id}")
    public ResponseEntity<EmailEventResponseDTO> updateEmailEvent(
            @PathVariable Integer id,
            @RequestBody EmailEventCreateRequestDTO dto
    ) {
        return ResponseEntity.ok(emailEventService.updateEmailEvent(id, dto));
    }

    // DELETE email event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmailEvent(@PathVariable Integer id) {
        emailEventService.deleteEmailEvent(id);
        return ResponseEntity.noContent().build();
    }
}

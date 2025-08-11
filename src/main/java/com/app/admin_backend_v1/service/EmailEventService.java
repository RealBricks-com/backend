package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.email_event.EmailEventCreateRequestDTO;
import com.app.admin_backend_v1.dto.email_event.EmailEventResponseDTO;
import com.app.admin_backend_v1.entity.EmailEvent;
import com.app.admin_backend_v1.entity.Lead;
import com.app.admin_backend_v1.repository.EmailEventRepository;
import com.app.admin_backend_v1.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailEventService {

    private final EmailEventRepository emailEventRepository;
    private final LeadRepository leadRepository;

    public EmailEventService(EmailEventRepository emailEventRepository, LeadRepository leadRepository) {
        this.emailEventRepository = emailEventRepository;
        this.leadRepository = leadRepository;
    }

    public List<EmailEventResponseDTO> getAllEmailEvents() {
        return emailEventRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EmailEventResponseDTO getEmailEventById(Integer id) {
        EmailEvent event = emailEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmailEvent not found with id: " + id));
        return toResponseDTO(event);
    }

    public EmailEventResponseDTO createEmailEvent(EmailEventCreateRequestDTO dto) {
        Lead lead = leadRepository.findById(dto.getLeadId())
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + dto.getLeadId()));

        EmailEvent event = new EmailEvent();
        event.setLead(lead);
        event.setEmailType(dto.getEmailType());
        event.setRecipientEmail(dto.getRecipientEmail());
        event.setSubject(dto.getSubject());
        event.setBody(dto.getBody());
        event.setStatus("pending");
        event.setCreatedAt(Instant.now());

        return toResponseDTO(emailEventRepository.save(event));
    }

    public EmailEventResponseDTO updateEmailEvent(Integer id, EmailEventCreateRequestDTO dto) {
        EmailEvent event = emailEventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmailEvent not found with id: " + id));

        Lead lead = leadRepository.findById(dto.getLeadId())
                .orElseThrow(() -> new RuntimeException("Lead not found with id: " + dto.getLeadId()));

        event.setLead(lead);
        event.setEmailType(dto.getEmailType());
        event.setRecipientEmail(dto.getRecipientEmail());
        event.setSubject(dto.getSubject());
        event.setBody(dto.getBody());
        // status intentionally not reset to pending unless you want it
        return toResponseDTO(emailEventRepository.save(event));
    }

    public void deleteEmailEvent(Integer id) {
        if (!emailEventRepository.existsById(id)) {
            throw new RuntimeException("EmailEvent not found with id: " + id);
        }
        emailEventRepository.deleteById(id);
    }

    private EmailEventResponseDTO toResponseDTO(EmailEvent event) {
        EmailEventResponseDTO dto = new EmailEventResponseDTO();
        dto.setId(event.getId());
        dto.setEmailType(event.getEmailType());
        dto.setRecipientEmail(event.getRecipientEmail());
        dto.setSubject(event.getSubject());
        dto.setBody(event.getBody());
        dto.setStatus(event.getStatus());
        dto.setCreatedAt(event.getCreatedAt());
        return dto;
    }
}

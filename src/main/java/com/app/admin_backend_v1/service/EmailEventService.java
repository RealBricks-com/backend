package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.email_event.EmailEventCreateRequestDTO;
import com.app.admin_backend_v1.dto.email_event.EmailEventResponseDTO;
import com.app.admin_backend_v1.entity.EmailEvent;
import com.app.admin_backend_v1.entity.Lead;
import com.app.admin_backend_v1.repository.EmailEventRepository;
import com.app.admin_backend_v1.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailEventService {

    @Autowired
    private EmailEventRepository emailEventRepository;

    @Autowired
    private LeadRepository leadRepository;

    public EmailEventResponseDTO createEmailEvent(EmailEventCreateRequestDTO dto) {
        Optional<Lead> leadOptional = leadRepository.findById(dto.getLeadId());
        if (leadOptional.isEmpty()) {
            throw new RuntimeException("Lead not found with id: " + dto.getLeadId());
        }

        EmailEvent event = new EmailEvent();
        event.setLead(leadOptional.get());
        event.setEmailType(dto.getEmailType());
        event.setRecipientEmail(dto.getRecipientEmail());
        event.setSubject(dto.getSubject());
        event.setBody(dto.getBody());
        event.setStatus("pending");

        EmailEvent saved = emailEventRepository.save(event);

        EmailEventResponseDTO response = new EmailEventResponseDTO();
        response.setId(saved.getId());
        response.setEmailType(saved.getEmailType());
        response.setRecipientEmail(saved.getRecipientEmail());
        response.setSubject(saved.getSubject());
        response.setBody(saved.getBody());
        response.setStatus(saved.getStatus());
        response.setCreatedAt(saved.getCreatedAt());
        return response;
    }
}

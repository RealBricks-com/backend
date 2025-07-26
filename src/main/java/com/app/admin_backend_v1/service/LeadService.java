package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.LeadCreateDTO;
import com.app.admin_backend_v1.dto.LeadDTO;
import com.app.admin_backend_v1.entity.DeveloperCore;
import com.app.admin_backend_v1.entity.Lead;
import com.app.admin_backend_v1.entity.ProjectCore;
import com.app.admin_backend_v1.entity.UserAuth;
import com.app.admin_backend_v1.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadService {

    @Autowired
    private LeadRepository leadRepository;

    private LeadDTO toDTO(Lead lead) {
        LeadDTO dto = new LeadDTO();
        dto.setId(lead.getId());
        dto.setProjectId(lead.getProject() != null ? lead.getProject().getId() : null);
        dto.setDeveloperId(lead.getDeveloper() != null ? lead.getDeveloper().getId() : null);
        dto.setUserId(lead.getUser() != null ? lead.getUser().getId() : null);
        dto.setName(lead.getName());
        dto.setEmail(lead.getEmail());
        dto.setPhone(lead.getPhone());
        dto.setBudgetMin(lead.getBudgetMin());
        dto.setBudgetMax(lead.getBudgetMax());
        dto.setLeadStatus(lead.getLeadStatus());
        dto.setLeadScore(lead.getLeadScore());
        dto.setCreatedAt(lead.getCreatedAt());
        return dto;
    }

    private Lead toEntity(LeadCreateDTO dto) {
        Lead lead = new Lead();
        ProjectCore project = new ProjectCore();
        project.setId(dto.getProjectId());
        lead.setProject(project);
        DeveloperCore developer = new DeveloperCore();
        developer.setId(dto.getDeveloperId());
        lead.setDeveloper(developer);
        UserAuth user = new UserAuth();
        user.setId(dto.getUserId());
        lead.setUser(user);
        lead.setName(dto.getName());
        lead.setEmail(dto.getEmail());
        lead.setPhone(dto.getPhone());
        lead.setBudgetMin(dto.getBudgetMin());
        lead.setBudgetMax(dto.getBudgetMax());
        lead.setLeadStatus(dto.getLeadStatus());
        lead.setLeadScore(dto.getLeadScore());
        lead.setCreatedAt(Instant.now());
        return lead;
    }

    public LeadDTO createLead(LeadCreateDTO dto) {
        Lead saved = leadRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public LeadDTO updateLead(Integer id, LeadDTO dto) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found"));

        ProjectCore project = new ProjectCore();
        project.setId(dto.getProjectId());
        lead.setProject(project);
        DeveloperCore developer = new DeveloperCore();
        developer.setId(dto.getDeveloperId());
        lead.setDeveloper(developer);
        UserAuth user = new UserAuth();
        user.setId(dto.getUserId());
        lead.setUser(user);
        lead.setName(dto.getName());
        lead.setEmail(dto.getEmail());
        lead.setPhone(dto.getPhone());
        lead.setBudgetMin(dto.getBudgetMin());
        lead.setBudgetMax(dto.getBudgetMax());
        lead.setLeadStatus(dto.getLeadStatus());
        lead.setLeadScore(dto.getLeadScore());
        Lead updated = leadRepository.save(lead);
        return toDTO(updated);
    }

    public LeadDTO getLeadById(Integer id) {
        return leadRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Lead not found"));
    }

    public List<LeadDTO> getAllLeads() {
        return leadRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteLead(Integer id) {
        if (!leadRepository.existsById(id)) {
            throw new RuntimeException("Lead not found");
        }
        leadRepository.deleteById(id);
    }
}
package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.LeadCreateDTO;
import com.app.admin_backend_v1.dto.LeadDTO;
import com.app.admin_backend_v1.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/leads")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @PostMapping
    public LeadDTO createLead(@RequestBody LeadCreateDTO dto) {
        return leadService.createLead(dto);
    }

    @PutMapping("/{id}")
    public LeadDTO updateLead(@PathVariable Integer id, @RequestBody LeadDTO dto) {
        return leadService.updateLead(id, dto);
    }

    @GetMapping("/{id}")
    public LeadDTO getLeadById(@PathVariable Integer id) {
        return leadService.getLeadById(id);
    }

    @GetMapping
    public List<LeadDTO> getAllLeads() {
        return leadService.getAllLeads();
    }

    @DeleteMapping("/{id}")
    public void deleteLead(@PathVariable Integer id) {
        leadService.deleteLead(id);
    }
}
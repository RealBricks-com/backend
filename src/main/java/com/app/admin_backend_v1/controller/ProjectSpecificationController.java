package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.ProjectSpecificationCreateDTO;
import com.app.admin_backend_v1.dto.ProjectSpecificationDTO;
import com.app.admin_backend_v1.service.ProjectSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/project-specifications")
public class ProjectSpecificationController {

    @Autowired
    private ProjectSpecificationService projectSpecificationService;

    @PostMapping
    public ProjectSpecificationDTO createProjectSpecification(@RequestBody ProjectSpecificationCreateDTO dto) {
        return projectSpecificationService.createProjectSpecification(dto);
    }

    @PutMapping("/{id}")
    public ProjectSpecificationDTO updateProjectSpecification(@PathVariable Integer id, @RequestBody ProjectSpecificationDTO dto) {
        return projectSpecificationService.updateProjectSpecification(id, dto);
    }

    @GetMapping("/{id}")
    public ProjectSpecificationDTO getProjectSpecificationById(@PathVariable Integer id) {
        return projectSpecificationService.getProjectSpecificationById(id);
    }

    @GetMapping
    public List<ProjectSpecificationDTO> getAllProjectSpecifications() {
        return projectSpecificationService.getAllProjectSpecifications();
    }

    @DeleteMapping("/{id}")
    public void deleteProjectSpecification(@PathVariable Integer id) {
        projectSpecificationService.deleteProjectSpecification(id);
    }
}
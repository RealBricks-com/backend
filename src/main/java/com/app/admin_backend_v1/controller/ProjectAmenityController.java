package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.ProjectAmenityCreateDTO;
import com.app.admin_backend_v1.dto.ProjectAmenityDTO;
import com.app.admin_backend_v1.service.ProjectAmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/project-amenities")
public class ProjectAmenityController {

    @Autowired
    private ProjectAmenityService projectAmenityService;

    @PostMapping
    public ProjectAmenityDTO createProjectAmenity(@RequestBody ProjectAmenityCreateDTO dto) {
        return projectAmenityService.createProjectAmenity(dto);
    }

    @PutMapping("/{projectId}/{amenityId}")
    public ProjectAmenityDTO updateProjectAmenity(@PathVariable Integer projectId, @PathVariable Integer amenityId, @RequestBody ProjectAmenityDTO dto) {
        return projectAmenityService.updateProjectAmenity(projectId, amenityId, dto);
    }

    @GetMapping("/{projectId}/{amenityId}")
    public ProjectAmenityDTO getProjectAmenityById(@PathVariable Integer projectId, @PathVariable Integer amenityId) {
        return projectAmenityService.getProjectAmenityById(projectId, amenityId);
    }

    @GetMapping
    public List<ProjectAmenityDTO> getAllProjectAmenities() {
        return projectAmenityService.getAllProjectAmenities();
    }

    @DeleteMapping("/{projectId}/{amenityId}")
    public void deleteProjectAmenity(@PathVariable Integer projectId, @PathVariable Integer amenityId) {
        projectAmenityService.deleteProjectAmenity(projectId, amenityId);
    }
}
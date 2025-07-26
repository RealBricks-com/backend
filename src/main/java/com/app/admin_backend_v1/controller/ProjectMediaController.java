package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.ProjectMediaCreateDTO;
import com.app.admin_backend_v1.dto.ProjectMediaDTO;
import com.app.admin_backend_v1.service.ProjectMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/project-media")
public class ProjectMediaController {

    @Autowired
    private ProjectMediaService projectMediaService;

    @PostMapping
    public ProjectMediaDTO createProjectMedia(@RequestBody ProjectMediaCreateDTO dto) {
        return projectMediaService.createProjectMedia(dto);
    }

    @PutMapping("/{id}")
    public ProjectMediaDTO updateProjectMedia(@PathVariable Integer id, @RequestBody ProjectMediaDTO dto) {
        return projectMediaService.updateProjectMedia(id, dto);
    }

    @GetMapping("/{id}")
    public ProjectMediaDTO getProjectMediaById(@PathVariable Integer id) {
        return projectMediaService.getProjectMediaById(id);
    }

    @GetMapping
    public List<ProjectMediaDTO> getAllProjectMedia() {
        return projectMediaService.getAllProjectMedia();
    }

    @DeleteMapping("/{id}")
    public void deleteProjectMedia(@PathVariable Integer id) {
        projectMediaService.deleteProjectMedia(id);
    }
}
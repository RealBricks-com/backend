package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.ProjectCoreCreateDTO;
import com.app.admin_backend_v1.dto.ProjectCoreDTO;
import com.app.admin_backend_v1.service.ProjectCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/project-cores")
public class ProjectCoreController {

    @Autowired
    private ProjectCoreService projectCoreService;

    @PostMapping
    public ProjectCoreDTO createProjectCore(@RequestBody ProjectCoreCreateDTO dto) {
        return projectCoreService.createProjectCore(dto);
    }

    @PutMapping("/{id}")
    public ProjectCoreDTO updateProjectCore(@PathVariable Integer id, @RequestBody ProjectCoreDTO dto) {
        return projectCoreService.updateProjectCore(id, dto);
    }

    @GetMapping("/{id}")
    public ProjectCoreDTO getProjectCoreById(@PathVariable Integer id) {
        return projectCoreService.getProjectCoreById(id);
    }

    @GetMapping
    public List<ProjectCoreDTO> getAllProjectCores() {
        return projectCoreService.getAllProjectCores();
    }

    @DeleteMapping("/{id}")
    public void deleteProjectCore(@PathVariable Integer id) {
        projectCoreService.deleteProjectCore(id);
    }
}
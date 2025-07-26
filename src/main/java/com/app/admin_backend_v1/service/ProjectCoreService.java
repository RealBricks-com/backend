package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.ProjectCoreCreateDTO;
import com.app.admin_backend_v1.dto.ProjectCoreDTO;
import com.app.admin_backend_v1.entity.Area;
import com.app.admin_backend_v1.entity.DeveloperCore;
import com.app.admin_backend_v1.entity.ProjectCore;
import com.app.admin_backend_v1.repository.ProjectCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectCoreService {

    @Autowired
    private ProjectCoreRepository projectCoreRepository;

    private ProjectCoreDTO toDTO(ProjectCore projectCore) {
        ProjectCoreDTO dto = new ProjectCoreDTO();
        dto.setId(projectCore.getId());
        dto.setReraId(projectCore.getReraId());
        dto.setName(projectCore.getName());
        dto.setSlug(projectCore.getSlug());
        dto.setDescription(projectCore.getDescription());
        dto.setDeveloperId(projectCore.getDeveloper() != null ? projectCore.getDeveloper().getId() : null);
        dto.setAreaId(projectCore.getArea() != null ? projectCore.getArea().getId() : null);
        dto.setPropertyType(projectCore.getPropertyType());
        dto.setCarpetAreaSqft(projectCore.getCarpetAreaSqft());
        dto.setMinPrice(projectCore.getMinPrice());
        dto.setStatus(projectCore.getStatus());
        dto.setCreatedAt(projectCore.getCreatedAt());
        return dto;
    }

    private ProjectCore toEntity(ProjectCoreCreateDTO dto) {
        ProjectCore projectCore = new ProjectCore();
        projectCore.setReraId(dto.getReraId());
        projectCore.setName(dto.getName());
        projectCore.setSlug(dto.getSlug());
        projectCore.setDescription(dto.getDescription());
        DeveloperCore developer = new DeveloperCore();
        developer.setId(dto.getDeveloperId());
        projectCore.setDeveloper(developer);
        Area area = new Area();
        area.setId(dto.getAreaId());
        projectCore.setArea(area);
        projectCore.setPropertyType(dto.getPropertyType());
        projectCore.setCarpetAreaSqft(dto.getCarpetAreaSqft());
        projectCore.setMinPrice(dto.getMinPrice());
        projectCore.setStatus(dto.getStatus());
        projectCore.setCreatedAt(Instant.now());
        return projectCore;
    }

    public ProjectCoreDTO createProjectCore(ProjectCoreCreateDTO dto) {
        ProjectCore saved = projectCoreRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public ProjectCoreDTO updateProjectCore(Integer id, ProjectCoreDTO dto) {
        ProjectCore projectCore = projectCoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectCore not found"));

        projectCore.setReraId(dto.getReraId());
        projectCore.setName(dto.getName());
        projectCore.setSlug(dto.getSlug());
        projectCore.setDescription(dto.getDescription());
        DeveloperCore developer = new DeveloperCore();
        developer.setId(dto.getDeveloperId());
        projectCore.setDeveloper(developer);
        Area area = new Area();
        area.setId(dto.getAreaId());
        projectCore.setArea(area);
        projectCore.setPropertyType(dto.getPropertyType());
        projectCore.setCarpetAreaSqft(dto.getCarpetAreaSqft());
        projectCore.setMinPrice(dto.getMinPrice());
        projectCore.setStatus(dto.getStatus());
        ProjectCore updated = projectCoreRepository.save(projectCore);
        return toDTO(updated);
    }

    public ProjectCoreDTO getProjectCoreById(Integer id) {
        return projectCoreRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("ProjectCore not found"));
    }

    public List<ProjectCoreDTO> getAllProjectCores() {
        return projectCoreRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteProjectCore(Integer id) {
        if (!projectCoreRepository.existsById(id)) {
            throw new RuntimeException("ProjectCore not found");
        }
        projectCoreRepository.deleteById(id);
    }
}
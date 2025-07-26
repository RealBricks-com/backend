package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.ProjectSpecificationCreateDTO;
import com.app.admin_backend_v1.dto.ProjectSpecificationDTO;
import com.app.admin_backend_v1.entity.ProjectCore;
import com.app.admin_backend_v1.entity.ProjectSpecification;
import com.app.admin_backend_v1.repository.ProjectSpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectSpecificationService {

    @Autowired
    private ProjectSpecificationRepository projectSpecificationRepository;

    private ProjectSpecificationDTO toDTO(ProjectSpecification projectSpecification) {
        ProjectSpecificationDTO dto = new ProjectSpecificationDTO();
        dto.setId(projectSpecification.getId());
        dto.setProjectId(projectSpecification.getProject() != null ? projectSpecification.getProject().getId() : null);
        dto.setSpecCategory(projectSpecification.getSpecCategory());
        dto.setSpecName(projectSpecification.getSpecName());
        dto.setSpecValue(projectSpecification.getSpecValue());
        dto.setBrandName(projectSpecification.getBrandName());
        dto.setDescription(projectSpecification.getDescription());
        dto.setDisplayOrder(projectSpecification.getDisplayOrder());
        return dto;
    }

    private ProjectSpecification toEntity(ProjectSpecificationCreateDTO dto) {
        ProjectSpecification projectSpecification = new ProjectSpecification();
        ProjectCore project = new ProjectCore();
        project.setId(dto.getProjectId());
        projectSpecification.setProject(project);
        projectSpecification.setSpecCategory(dto.getSpecCategory());
        projectSpecification.setSpecName(dto.getSpecName());
        projectSpecification.setSpecValue(dto.getSpecValue());
        projectSpecification.setBrandName(dto.getBrandName());
        projectSpecification.setDescription(dto.getDescription());
        projectSpecification.setDisplayOrder(dto.getDisplayOrder());
        return projectSpecification;
    }

    public ProjectSpecificationDTO createProjectSpecification(ProjectSpecificationCreateDTO dto) {
        ProjectSpecification saved = projectSpecificationRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public ProjectSpecificationDTO updateProjectSpecification(Integer id, ProjectSpecificationDTO dto) {
        ProjectSpecification projectSpecification = projectSpecificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectSpecification not found"));

        ProjectCore project = new ProjectCore();
        project.setId(dto.getProjectId());
        projectSpecification.setProject(project);
        projectSpecification.setSpecCategory(dto.getSpecCategory());
        projectSpecification.setSpecName(dto.getSpecName());
        projectSpecification.setSpecValue(dto.getSpecValue());
        projectSpecification.setBrandName(dto.getBrandName());
        projectSpecification.setDescription(dto.getDescription());
        projectSpecification.setDisplayOrder(dto.getDisplayOrder());
        ProjectSpecification updated = projectSpecificationRepository.save(projectSpecification);
        return toDTO(updated);
    }

    public ProjectSpecificationDTO getProjectSpecificationById(Integer id) {
        return projectSpecificationRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("ProjectSpecification not found"));
    }

    public List<ProjectSpecificationDTO> getAllProjectSpecifications() {
        return projectSpecificationRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteProjectSpecification(Integer id) {
        if (!projectSpecificationRepository.existsById(id)) {
            throw new RuntimeException("ProjectSpecification not found");
        }
        projectSpecificationRepository.deleteById(id);
    }
}
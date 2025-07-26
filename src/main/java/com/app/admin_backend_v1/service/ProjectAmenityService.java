package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.ProjectAmenityCreateDTO;
import com.app.admin_backend_v1.dto.ProjectAmenityDTO;
import com.app.admin_backend_v1.entity.Amenity;
import com.app.admin_backend_v1.entity.ProjectAmenity;
import com.app.admin_backend_v1.entity.ProjectAmenityId;
import com.app.admin_backend_v1.entity.ProjectCore;
import com.app.admin_backend_v1.repository.ProjectAmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectAmenityService {

    @Autowired
    private ProjectAmenityRepository projectAmenityRepository;

    private ProjectAmenityDTO toDTO(ProjectAmenity projectAmenity) {
        ProjectAmenityDTO dto = new ProjectAmenityDTO();
        dto.setProjectId(projectAmenity.getId().getProjectId());
        dto.setAmenityId(projectAmenity.getId().getAmenityId());
        dto.setCreatedAt(projectAmenity.getCreatedAt());
        return dto;
    }

    private ProjectAmenity toEntity(ProjectAmenityCreateDTO dto) {
        ProjectAmenity projectAmenity = new ProjectAmenity();
        ProjectAmenityId id = new ProjectAmenityId();
        id.setProjectId(dto.getProjectId());
        id.setAmenityId(dto.getAmenityId());
        projectAmenity.setId(id);
        ProjectCore project = new ProjectCore();
        project.setId(dto.getProjectId());
        projectAmenity.setProject(project);
        Amenity amenity = new Amenity();
        amenity.setId(dto.getAmenityId());
        projectAmenity.setAmenity(amenity);
        projectAmenity.setCreatedAt(Instant.now());
        return projectAmenity;
    }

    public ProjectAmenityDTO createProjectAmenity(ProjectAmenityCreateDTO dto) {
        ProjectAmenity saved = projectAmenityRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public ProjectAmenityDTO updateProjectAmenity(Integer projectId, Integer amenityId, ProjectAmenityDTO dto) {
        ProjectAmenityId id = new ProjectAmenityId();
        id.setProjectId(projectId);
        id.setAmenityId(amenityId);
        ProjectAmenity projectAmenity = projectAmenityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectAmenity not found"));

        ProjectCore project = new ProjectCore();
        project.setId(dto.getProjectId());
        projectAmenity.setProject(project);
        Amenity amenity = new Amenity();
        amenity.setId(dto.getAmenityId());
        projectAmenity.setAmenity(amenity);
        ProjectAmenityId newId = new ProjectAmenityId();
        newId.setProjectId(dto.getProjectId());
        newId.setAmenityId(dto.getAmenityId());
        projectAmenity.setId(newId);
        ProjectAmenity updated = projectAmenityRepository.save(projectAmenity);
        return toDTO(updated);
    }

    public ProjectAmenityDTO getProjectAmenityById(Integer projectId, Integer amenityId) {
        ProjectAmenityId id = new ProjectAmenityId();
        id.setProjectId(projectId);
        id.setAmenityId(amenityId);
        return projectAmenityRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("ProjectAmenity not found"));
    }

    public List<ProjectAmenityDTO> getAllProjectAmenities() {
        return projectAmenityRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteProjectAmenity(Integer projectId, Integer amenityId) {
        ProjectAmenityId id = new ProjectAmenityId();
        id.setProjectId(projectId);
        id.setAmenityId(amenityId);
        if (!projectAmenityRepository.existsById(id)) {
            throw new RuntimeException("ProjectAmenity not found");
        }
        projectAmenityRepository.deleteById(id);
    }
}
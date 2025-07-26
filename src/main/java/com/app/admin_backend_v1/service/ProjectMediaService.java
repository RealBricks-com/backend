package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.ProjectMediaCreateDTO;
import com.app.admin_backend_v1.dto.ProjectMediaDTO;
import com.app.admin_backend_v1.entity.ProjectCore;
import com.app.admin_backend_v1.entity.ProjectMedia;
import com.app.admin_backend_v1.repository.ProjectMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectMediaService {

    @Autowired
    private ProjectMediaRepository projectMediaRepository;

    private ProjectMediaDTO toDTO(ProjectMedia projectMedia) {
        ProjectMediaDTO dto = new ProjectMediaDTO();
        dto.setId(projectMedia.getId());
        dto.setProjectId(projectMedia.getProject() != null ? projectMedia.getProject().getId() : null);
        dto.setFileUrl(projectMedia.getFileUrl());
        dto.setMediaType(projectMedia.getMediaType());
        dto.setCreatedAt(projectMedia.getCreatedAt());
        return dto;
    }

    private ProjectMedia toEntity(ProjectMediaCreateDTO dto) {
        ProjectMedia projectMedia = new ProjectMedia();
        ProjectCore project = new ProjectCore();
        project.setId(dto.getProjectId());
        projectMedia.setProject(project);
        projectMedia.setFileUrl(dto.getFileUrl());
        projectMedia.setMediaType(dto.getMediaType());
        projectMedia.setCreatedAt(Instant.now());
        return projectMedia;
    }

    public ProjectMediaDTO createProjectMedia(ProjectMediaCreateDTO dto) {
        ProjectMedia saved = projectMediaRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public ProjectMediaDTO updateProjectMedia(Integer id, ProjectMediaDTO dto) {
        ProjectMedia projectMedia = projectMediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectMedia not found"));

        ProjectCore project = new ProjectCore();
        project.setId(dto.getProjectId());
        projectMedia.setProject(project);
        projectMedia.setFileUrl(dto.getFileUrl());
        projectMedia.setMediaType(dto.getMediaType());
        ProjectMedia updated = projectMediaRepository.save(projectMedia);
        return toDTO(updated);
    }

    public ProjectMediaDTO getProjectMediaById(Integer id) {
        return projectMediaRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("ProjectMedia not found"));
    }

    public List<ProjectMediaDTO> getAllProjectMedia() {
        return projectMediaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteProjectMedia(Integer id) {
        if (!projectMediaRepository.existsById(id)) {
            throw new RuntimeException("ProjectMedia not found");
        }
        projectMediaRepository.deleteById(id);
    }
}
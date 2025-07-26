package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.developer.*;
import com.app.admin_backend_v1.entity.DeveloperCore;
import com.app.admin_backend_v1.repository.DeveloperCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperCoreService {

    @Autowired
    private DeveloperCoreRepository developerCoreRepository;

    public DeveloperCoreResponseDTO create(DeveloperCoreCreateDTO dto) {
        DeveloperCore developer = new DeveloperCore();
        developer.setReraId(dto.getReraId());
        developer.setName(dto.getName());
        developer.setEmail(dto.getEmail());
        developer.setPhone(dto.getPhone());

        DeveloperCore saved = developerCoreRepository.save(developer);
        return toResponseDTO(saved);
    }

    public List<DeveloperCoreResponseDTO> getAll() {
        return developerCoreRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public DeveloperCoreResponseDTO getById(Integer id) {
        DeveloperCore developer = developerCoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found"));
        return toResponseDTO(developer);
    }

    public DeveloperCoreResponseDTO update(Integer id, DeveloperCoreUpdateDTO dto) {
        DeveloperCore developer = developerCoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found"));

        developer.setName(dto.getName());
        developer.setEmail(dto.getEmail());
        developer.setPhone(dto.getPhone());

        DeveloperCore updated = developerCoreRepository.save(developer);
        return toResponseDTO(updated);
    }

    public void delete(Integer id) {
        developerCoreRepository.deleteById(id);
    }

    private DeveloperCoreResponseDTO toResponseDTO(DeveloperCore developer) {
        DeveloperCoreResponseDTO dto = new DeveloperCoreResponseDTO();
        dto.setId(developer.getId());
        dto.setReraId(developer.getReraId());
        dto.setName(developer.getName());
        dto.setEmail(developer.getEmail());
        dto.setPhone(developer.getPhone());
        dto.setCreatedAt(developer.getCreatedAt());
        return dto;
    }
}

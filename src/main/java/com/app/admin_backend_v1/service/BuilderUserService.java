package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.builder.BuilderUserRequestDTO;
import com.app.admin_backend_v1.dto.builder.BuilderUserResponseDTO;
import com.app.admin_backend_v1.entity.BuilderUser;
import com.app.admin_backend_v1.entity.DeveloperCore;
import com.app.admin_backend_v1.repository.BuilderUserRepository;
import com.app.admin_backend_v1.repository.DeveloperCoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuilderUserService {

    private final BuilderUserRepository builderUserRepository;
    private final DeveloperCoreRepository developerCoreRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public BuilderUserResponseDTO create(BuilderUserRequestDTO dto) {
        DeveloperCore dev = developerCoreRepository.findById(dto.getDeveloperId())
                .orElseThrow(() -> new RuntimeException("Developer not found"));

        BuilderUser user = new BuilderUser();
        user.setDeveloper(dev);
        user.setReraId(dto.getReraId());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        BuilderUser saved = builderUserRepository.save(user);
        return toResponse(saved);
    }

    public List<BuilderUserResponseDTO> getAll() {
        return builderUserRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BuilderUserResponseDTO getById(Integer id) {
        BuilderUser user = builderUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return toResponse(user);
    }

    public BuilderUserResponseDTO update(Integer id, BuilderUserRequestDTO dto) {
        BuilderUser user = builderUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        DeveloperCore dev = developerCoreRepository.findById(dto.getDeveloperId())
                .orElseThrow(() -> new RuntimeException("Developer not found"));

        user.setDeveloper(dev);
        user.setReraId(dto.getReraId());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return toResponse(builderUserRepository.save(user));
    }

    public void delete(Integer id) {
        builderUserRepository.deleteById(id);
    }

    private BuilderUserResponseDTO toResponse(BuilderUser user) {
        BuilderUserResponseDTO dto = new BuilderUserResponseDTO();
        dto.setId(user.getId());
        dto.setDeveloperId(user.getDeveloper().getId());
        dto.setReraId(user.getReraId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}

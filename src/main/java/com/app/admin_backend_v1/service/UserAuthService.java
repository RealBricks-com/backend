package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.UserAuthCreateDTO;
import com.app.admin_backend_v1.dto.UserAuthDTO;
import com.app.admin_backend_v1.entity.UserAuth;
import com.app.admin_backend_v1.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    private UserAuthDTO toDTO(UserAuth userAuth) {
        UserAuthDTO dto = new UserAuthDTO();
        dto.setId(userAuth.getId());
        dto.setEmail(userAuth.getEmail());
        dto.setGoogleId(userAuth.getGoogleId());
        dto.setFirstName(userAuth.getFirstName());
        dto.setPhone(userAuth.getPhone());
        dto.setCreatedAt(userAuth.getCreatedAt());
        return dto;
    }

    private UserAuth toEntity(UserAuthCreateDTO dto) {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(dto.getEmail());
        userAuth.setGoogleId(dto.getGoogleId());
        userAuth.setFirstName(dto.getFirstName());
        userAuth.setPhone(dto.getPhone());
        userAuth.setCreatedAt(Instant.now());
        return userAuth;
    }

    public UserAuthDTO createUserAuth(UserAuthCreateDTO dto) {
        UserAuth saved = userAuthRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public UserAuthDTO updateUserAuth(Integer id, UserAuthDTO dto) {
        UserAuth userAuth = userAuthRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserAuth not found"));

        userAuth.setEmail(dto.getEmail());
        userAuth.setGoogleId(dto.getGoogleId());
        userAuth.setFirstName(dto.getFirstName());
        userAuth.setPhone(dto.getPhone());
        UserAuth updated = userAuthRepository.save(userAuth);
        return toDTO(updated);
    }

    public UserAuthDTO getUserAuthById(Integer id) {
        return userAuthRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("UserAuth not found"));
    }

    public List<UserAuthDTO> getAllUserAuths() {
        return userAuthRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteUserAuth(Integer id) {
        if (!userAuthRepository.existsById(id)) {
            throw new RuntimeException("UserAuth not found");
        }
        userAuthRepository.deleteById(id);
    }
}
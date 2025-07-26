package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.AmenityDTO;
import com.app.admin_backend_v1.entity.Amenity;
import com.app.admin_backend_v1.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    private AmenityDTO toDTO(Amenity amenity) {
        AmenityDTO dto = new AmenityDTO();
        dto.setId(amenity.getId());
        dto.setName(amenity.getName());
        dto.setCategory(amenity.getCategory());
        dto.setCreatedAt(amenity.getCreatedAt());
        return dto;
    }

    private Amenity toEntity(AmenityDTO dto) {
        Amenity amenity = new Amenity();
        amenity.setName(dto.getName());
        amenity.setCategory(dto.getCategory());
        amenity.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : Instant.now());
        return amenity;
    }

    public AmenityDTO createAmenity(AmenityDTO dto) {
        Amenity saved = amenityRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public AmenityDTO updateAmenity(Integer id, AmenityDTO dto) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));

        amenity.setName(dto.getName());
        amenity.setCategory(dto.getCategory());
        Amenity updated = amenityRepository.save(amenity);
        return toDTO(updated);
    }

    public AmenityDTO getAmenityById(Integer id) {
        return amenityRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Amenity not found"));
    }

    public List<AmenityDTO> getAllAmenities() {
        return amenityRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteAmenity(Integer id) {
        if (!amenityRepository.existsById(id)) {
            throw new RuntimeException("Amenity not found");
        }
        amenityRepository.deleteById(id);
    }
}

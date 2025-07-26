package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.DistrictDTO;
import com.app.admin_backend_v1.entity.City;
import com.app.admin_backend_v1.entity.District;
import com.app.admin_backend_v1.repository.CityRepository;
import com.app.admin_backend_v1.repository.DistrictRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    public List<DistrictDTO> getAllDistricts() {
        return districtRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public DistrictDTO getDistrictById(Integer id) {
        return districtRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("District not found"));
    }

    public DistrictDTO createDistrict(DistrictDTO dto) {
        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new EntityNotFoundException("City not found"));

        District district = new District();
        district.setId(dto.getId());
        district.setName(dto.getName());
        district.setCity(city);
        district.setCreatedAt(Instant.now());

        return mapToDTO(districtRepository.save(district));
    }

    public DistrictDTO updateDistrict(Integer id, DistrictDTO dto) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("District not found"));

        City city = cityRepository.findById(dto.getCityId())
                .orElseThrow(() -> new EntityNotFoundException("City not found"));

        district.setName(dto.getName());
        district.setCity(city);

        return mapToDTO(districtRepository.save(district));
    }

    public void deleteDistrict(Integer id) {
        districtRepository.deleteById(id);
    }

    private DistrictDTO mapToDTO(District district) {
        DistrictDTO dto = new DistrictDTO();
        dto.setId(district.getId());
        dto.setName(district.getName());
        dto.setCityId(district.getCity().getId());
        return dto;
    }
}

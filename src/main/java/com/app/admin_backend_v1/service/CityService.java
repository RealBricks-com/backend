package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.city.CityRequestDTO;
import com.app.admin_backend_v1.dto.city.CityResponseDTO;
import com.app.admin_backend_v1.entity.City;
import com.app.admin_backend_v1.entity.State;
import com.app.admin_backend_v1.repository.CityRepository;
import com.app.admin_backend_v1.repository.StateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public CityResponseDTO createCity(CityRequestDTO dto) {
        State state = stateRepository.findById(dto.getStateId())
                .orElseThrow(() -> new EntityNotFoundException("State not found"));

        City city = new City();
        city.setName(dto.getName());
        city.setState(state);
        City saved = cityRepository.save(city);

        return toDTO(saved);
    }

    public CityResponseDTO updateCity(Integer id, CityRequestDTO dto) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));

        State state = stateRepository.findById(dto.getStateId())
                .orElseThrow(() -> new EntityNotFoundException("State not found"));

        city.setName(dto.getName());
        city.setState(state);
        City updated = cityRepository.save(city);

        return toDTO(updated);
    }

    public List<CityResponseDTO> getAllCities() {
        return cityRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteCity(Integer id) {
        if (!cityRepository.existsById(id)) {
            throw new EntityNotFoundException("City not found");
        }
        cityRepository.deleteById(id);
    }

    public CityResponseDTO getCityById(Integer id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));
        return toDTO(city);
    }

    private CityResponseDTO toDTO(City city) {
        CityResponseDTO dto = new CityResponseDTO();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setCreatedAt(city.getCreatedAt());
        dto.setStateId(city.getState().getId());
        dto.setStateName(city.getState().getName());
        return dto;
    }
}

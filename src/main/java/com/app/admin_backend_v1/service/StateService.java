package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.StateDTO;
import com.app.admin_backend_v1.entity.Country;
import com.app.admin_backend_v1.entity.State;
import com.app.admin_backend_v1.repository.CountryRepository;
import com.app.admin_backend_v1.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    private StateDTO toDTO(State state) {
        StateDTO dto = new StateDTO();
        dto.setId(state.getId());
        dto.setName(state.getName());
        dto.setCreatedAt(state.getCreatedAt());
        dto.setCountryId(state.getCountry() != null ? state.getCountry().getId() : null);
        return dto;
    }

    private State toEntity(StateDTO dto) {
        State state = new State();
        state.setId(dto.getId());
        state.setName(dto.getName());
        state.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : Instant.now());

        if (dto.getCountryId() != null) {
            Country country = countryRepository.findById(dto.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found with ID: " + dto.getCountryId()));
            state.setCountry(country);
        }

        return state;
    }

    public StateDTO createState(StateDTO dto) {
        State saved = stateRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public StateDTO updateState(Integer id, StateDTO dto) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));

        state.setName(dto.getName());

        if (dto.getCountryId() != null) {
            Country country = countryRepository.findById(dto.getCountryId())
                    .orElseThrow(() -> new RuntimeException("Country not found with ID: " + dto.getCountryId()));
            state.setCountry(country);
        }

        State updated = stateRepository.save(state);
        return toDTO(updated);
    }

    public StateDTO getStateById(Integer id) {
        return stateRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("State not found with ID: " + id));
    }

    public List<StateDTO> getAllStates() {
        return stateRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteState(Integer id) {
        if (!stateRepository.existsById(id)) {
            throw new RuntimeException("State not found with ID: " + id);
        }
        stateRepository.deleteById(id);
    }
}

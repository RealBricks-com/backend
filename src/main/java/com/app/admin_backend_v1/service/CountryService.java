package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.CountryDTO;
import com.app.admin_backend_v1.entity.Country;
import com.app.admin_backend_v1.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    private CountryDTO toDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setCreatedAt(country.getCreatedAt());
        return dto;
    }

    private Country toEntity(CountryDTO dto) {
        Country country = new Country();
        country.setId(dto.getId());
        country.setName(dto.getName());
        country.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : Instant.now());
        return country;
    }

    public CountryDTO createCountry(CountryDTO dto) {
        Country saved = countryRepository.save(toEntity(dto));
        return toDTO(saved);
    }

    public CountryDTO updateCountry(Integer id, CountryDTO dto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
        country.setName(dto.getName());
        Country updated = countryRepository.save(country);
        return toDTO(updated);
    }

    public CountryDTO getCountryById(Integer id) {
        return countryRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteCountry(Integer id) {
        if (!countryRepository.existsById(id)) {
            throw new RuntimeException("Country not found");
        }
        countryRepository.deleteById(id);
    }
}

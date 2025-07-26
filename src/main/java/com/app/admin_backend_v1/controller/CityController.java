package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.city.CityRequestDTO;
import com.app.admin_backend_v1.dto.city.CityResponseDTO;
import com.app.admin_backend_v1.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public CityResponseDTO create(@RequestBody CityRequestDTO dto) {
        return cityService.createCity(dto);
    }

    @PutMapping("/{id}")
    public CityResponseDTO update(@PathVariable Integer id, @RequestBody CityRequestDTO dto) {
        return cityService.updateCity(id, dto);
    }

    @GetMapping
    public List<CityResponseDTO> getAll() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public CityResponseDTO getById(@PathVariable Integer id) {
        return cityService.getCityById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        cityService.deleteCity(id);
    }
}

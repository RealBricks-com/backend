package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.CountryDTO;
import com.app.admin_backend_v1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public CountryDTO createCountry(@RequestBody CountryDTO dto) {
        return countryService.createCountry(dto);
    }

    @PutMapping("/{id}")
    public CountryDTO updateCountry(@PathVariable Integer id, @RequestBody CountryDTO dto) {
        return countryService.updateCountry(id, dto);
    }

    @GetMapping("/{id}")
    public CountryDTO getCountryById(@PathVariable Integer id) {
        return countryService.getCountryById(id);
    }

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountries();
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Integer id) {
        countryService.deleteCountry(id);
    }
}

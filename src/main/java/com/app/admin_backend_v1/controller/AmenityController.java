package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.AmenityDTO;
import com.app.admin_backend_v1.service.AmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/amenities")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @PostMapping
    public AmenityDTO createAmenity(@RequestBody AmenityDTO dto) {
        return amenityService.createAmenity(dto);
    }

    @PutMapping("/{id}")
    public AmenityDTO updateAmenity(@PathVariable Integer id, @RequestBody AmenityDTO dto) {
        return amenityService.updateAmenity(id, dto);
    }

    @GetMapping("/{id}")
    public AmenityDTO getAmenityById(@PathVariable Integer id) {
        return amenityService.getAmenityById(id);
    }

    @GetMapping
    public List<AmenityDTO> getAllAmenities() {
        return amenityService.getAllAmenities();
    }

    @DeleteMapping("/{id}")
    public void deleteAmenity(@PathVariable Integer id) {
        amenityService.deleteAmenity(id);
    }
}

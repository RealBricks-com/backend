package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.DistrictDTO;
import com.app.admin_backend_v1.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/districts")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<DistrictDTO>> getAllDistricts() {
        return ResponseEntity.ok(districtService.getAllDistricts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistrictDTO> getDistrictById(@PathVariable Integer id) {
        return ResponseEntity.ok(districtService.getDistrictById(id));
    }

    @PostMapping
    public ResponseEntity<DistrictDTO> createDistrict(@RequestBody DistrictDTO dto) {
        return ResponseEntity.ok(districtService.createDistrict(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistrictDTO> updateDistrict(@PathVariable Integer id, @RequestBody DistrictDTO dto) {
        return ResponseEntity.ok(districtService.updateDistrict(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Integer id) {
        districtService.deleteDistrict(id);
        return ResponseEntity.noContent().build();
    }
}

package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.developer.*;
import com.app.admin_backend_v1.service.DeveloperCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperCoreController {

    @Autowired
    private DeveloperCoreService developerCoreService;

    @PostMapping
    public DeveloperCoreResponseDTO create(@RequestBody DeveloperCoreCreateDTO dto) {
        return developerCoreService.create(dto);
    }

    @GetMapping
    public List<DeveloperCoreResponseDTO> getAll() {
        return developerCoreService.getAll();
    }

    @GetMapping("/{id}")
    public DeveloperCoreResponseDTO getById(@PathVariable Integer id) {
        return developerCoreService.getById(id);
    }

    @PutMapping("/{id}")
    public DeveloperCoreResponseDTO update(@PathVariable Integer id,
                                           @RequestBody DeveloperCoreUpdateDTO dto) {
        return developerCoreService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        developerCoreService.delete(id);
    }
}

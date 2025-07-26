package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.builder.BuilderUserRequestDTO;
import com.app.admin_backend_v1.dto.builder.BuilderUserResponseDTO;
import com.app.admin_backend_v1.service.BuilderUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/builder-users")
@RequiredArgsConstructor
public class BuilderUserController {

    private final BuilderUserService service;

    @PostMapping
    public BuilderUserResponseDTO create(@RequestBody BuilderUserRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<BuilderUserResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BuilderUserResponseDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public BuilderUserResponseDTO update(@PathVariable Integer id, @RequestBody BuilderUserRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

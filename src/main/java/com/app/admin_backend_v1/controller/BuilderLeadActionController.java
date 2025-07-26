package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.builderleadaction.BuilderLeadActionUpdateDTO;
import com.app.admin_backend_v1.dto.builderleadaction.BuilderLeadActionResponseDTO;
import com.app.admin_backend_v1.service.BuilderLeadActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/builder-lead-actions")
@RequiredArgsConstructor
public class BuilderLeadActionController {

    private final BuilderLeadActionService service;

    @PutMapping("/{id}")
    public ResponseEntity<BuilderLeadActionResponseDTO> update(@PathVariable Integer id,
                                                               @RequestBody BuilderLeadActionUpdateDTO dto) {
        BuilderLeadActionResponseDTO response = service.updateBuilderLeadAction(id, dto);
        return ResponseEntity.ok(response);
    }
}

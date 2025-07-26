package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.dto.builderleadaction.BuilderLeadActionUpdateDTO;
import com.app.admin_backend_v1.dto.builderleadaction.BuilderLeadActionResponseDTO;
import com.app.admin_backend_v1.entity.BuilderLeadAction;
import com.app.admin_backend_v1.entity.DeveloperCore;
import com.app.admin_backend_v1.repository.BuilderLeadActionRepository;
import com.app.admin_backend_v1.repository.DeveloperCoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuilderLeadActionService {

    private final BuilderLeadActionRepository actionRepository;
    private final DeveloperCoreRepository developerRepo;

    public BuilderLeadActionResponseDTO updateBuilderLeadAction(Integer actionId, BuilderLeadActionUpdateDTO dto) {
        BuilderLeadAction action = actionRepository.findById(actionId)
                .orElseThrow(() -> new RuntimeException("Action not found"));

        if (dto.getDeveloperId() != null) {
            DeveloperCore dev = developerRepo.findById(dto.getDeveloperId())
                    .orElseThrow(() -> new RuntimeException("Developer not found"));
            action.setDeveloper(dev);
        }

        if (dto.getActionType() != null) {
            action.setActionType(dto.getActionType());
        }

        if (dto.getNotes() != null) {
            action.setNotes(dto.getNotes());
        }

        BuilderLeadAction updated = actionRepository.save(action);

        BuilderLeadActionResponseDTO response = new BuilderLeadActionResponseDTO();
        response.setId(updated.getId());
        response.setLeadId(updated.getLead().getId());
        response.setDeveloperId(updated.getDeveloper().getId());
        response.setActionType(updated.getActionType());
        response.setNotes(updated.getNotes());
        response.setCreatedAt(updated.getCreatedAt());
        return response;
    }
}

package com.app.admin_backend_v1.controller;

import com.app.admin_backend_v1.dto.StateDTO;
import com.app.admin_backend_v1.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping
    public StateDTO createState(@RequestBody StateDTO dto) {
        return stateService.createState(dto);
    }

    @PutMapping("/{id}")
    public StateDTO updateState(@PathVariable Integer id, @RequestBody StateDTO dto) {
        return stateService.updateState(id, dto);
    }

    @GetMapping("/{id}")
    public StateDTO getStateById(@PathVariable Integer id) {
        return stateService.getStateById(id);
    }

    @GetMapping
    public List<StateDTO> getAllStates() {
        return stateService.getAllStates();
    }

    @DeleteMapping("/{id}")
    public void deleteState(@PathVariable Integer id) {
        stateService.deleteState(id);
    }
}

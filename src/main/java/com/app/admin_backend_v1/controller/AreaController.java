package com.app.admin_backend_v1.controller;


import com.app.admin_backend_v1.entity.Area;
import com.app.admin_backend_v1.service.AreaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {

    private AreaService areaService;
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    // -- methods

    @GetMapping
    public List<Area> getAllAreas(){
        return areaService.getAreas();
    }
}

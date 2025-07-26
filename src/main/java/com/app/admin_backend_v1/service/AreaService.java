package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.entity.Area;
import com.app.admin_backend_v1.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Service
public class AreaService {

    private final AreaRepository areaRepository;
    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    // -- methods

    public List<Area> getAreas(){
        return areaRepository.findAll();
    }

    public Area getAreaById(Integer id){
        return areaRepository.findById(id).get();
    }



}

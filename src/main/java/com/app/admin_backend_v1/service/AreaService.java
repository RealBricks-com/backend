package com.app.admin_backend_v1.service;

import com.app.admin_backend_v1.entity.Area;
import com.app.admin_backend_v1.repository.AreaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }

    public Optional<Area> getAreaById(Integer id) {
        return areaRepository.findById(id);
    }

    public Area createArea(Area area) {
        // Set createdAt if null
        if (area.getCreatedAt() == null) {
            area.setCreatedAt(Instant.now());
        }
        return areaRepository.save(area);
    }

    public Area updateArea(Integer id, Area areaDetails) {
        return areaRepository.findById(id)
                .map(existing -> {
                    existing.setName(areaDetails.getName());
                    existing.setPincode(areaDetails.getPincode());
                    existing.setSubDistrict(areaDetails.getSubDistrict());
                    return areaRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Area not found with id " + id));
    }

    public void deleteArea(Integer id) {
        if (!areaRepository.existsById(id)) {
            throw new RuntimeException("Area not found with id " + id);
        }
        areaRepository.deleteById(id);
    }
}

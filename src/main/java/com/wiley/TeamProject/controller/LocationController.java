package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.entity.Location;
import com.wiley.TeamProject.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService service;

    @PostMapping
    public Location add(@RequestBody Location l) {
        return service.addLocation(l);
    }

    @GetMapping
    public List<Location> all() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteLocation(id);

    }

}

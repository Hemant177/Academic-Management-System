package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.entity.Intake;
import com.wiley.TeamProject.service.IntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intakes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class IntakeController {

    private final IntakeService service;

    @PostMapping
    public Intake create(@RequestBody Intake intake) {
        return service.createIntake(intake);
    }

    @GetMapping("/course/{courseId}")
    public List<Intake> getByCourse(@PathVariable Long courseId) {
        return service.getByCourse(courseId);
    }

    @GetMapping("/count")
    public long countIntakes() {
        return service.countIntakes();
    }

    @GetMapping
    public List<Intake> getAll() {
        return service.getAll();
    }

}

package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.entity.StudentEnrollment;
import com.wiley.TeamProject.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService service;

    @PostMapping
    public StudentEnrollment enroll(
            @RequestParam Long studentId,
            @RequestParam Long courseId,
            @RequestParam Long intakeId
    ) {
        return service.enroll(studentId, courseId, intakeId);
    }

    @GetMapping
    public List<StudentEnrollment> all() {
        return service.getAll();
    }
}

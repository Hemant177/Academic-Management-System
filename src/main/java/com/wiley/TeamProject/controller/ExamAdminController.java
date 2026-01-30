package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.dto.CreateExamRequestDTO;
import com.wiley.TeamProject.service.ExamAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/exam")
@RequiredArgsConstructor
public class ExamAdminController {

    private final ExamAdminService examAdminService;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<String> createExam(@RequestBody CreateExamRequestDTO request) {
        System.out.println("CREATE EXAM API HIT");
        System.out.println("Course ID: " + request.getCourseId());
        System.out.println("Questions size: " +
                (request.getQuestions() == null ? "NULL" : request.getQuestions().size()));
        examAdminService.createExam(request);
        return ResponseEntity.ok("Exam created successfully");
    }


}

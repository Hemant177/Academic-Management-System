package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.dto.ExamResultResponse;
import com.wiley.TeamProject.dto.StartExamResponse;
import com.wiley.TeamProject.dto.SubmitExamRequest;
import com.wiley.TeamProject.service.ExamEvaluationService;
import com.wiley.TeamProject.service.ExamFlowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
@CrossOrigin
public class StudentExamController {

    private final ExamEvaluationService examEvaluationService;
    private final ExamFlowService examFlowService;

    @GetMapping("/start")
    public StartExamResponse startExam(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {

        return examFlowService.startExam(studentId, courseId);
    }
    @PostMapping("/submit/{examId}")
    public ExamResultResponse submitExam(
            @PathVariable Long examId,
            @RequestBody SubmitExamRequest request) {

        return examEvaluationService.submitExam(examId, request);
    }
}





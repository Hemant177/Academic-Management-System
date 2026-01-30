package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.ExamResultResponse;
import com.wiley.TeamProject.dto.SubmitExamRequest;

public interface ExamEvaluationService {
    ExamResultResponse submitExam(Long examId, SubmitExamRequest request);
}

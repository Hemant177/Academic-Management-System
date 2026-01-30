package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.StartExamResponse;

public interface ExamFlowService {

    StartExamResponse startExam(Long studentId, Long courseId);
}

package com.wiley.TeamProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExamResultResponse {
    private String studentName;
    private String courseName;
    private Integer score;
    private String resultStatus; // PASS / FAIL
}

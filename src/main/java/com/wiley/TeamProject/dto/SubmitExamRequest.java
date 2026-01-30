package com.wiley.TeamProject.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmitExamRequest {
    private Long studentId;
    private Long courseId;
    private List<AnswerDTO> answers;
}

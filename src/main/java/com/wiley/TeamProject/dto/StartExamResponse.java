package com.wiley.TeamProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StartExamResponse {

    private Long examId;
    private Integer durationMinutes;
    private List<QuestionResponseDTO> questions;
}

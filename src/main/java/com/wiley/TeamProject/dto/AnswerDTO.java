package com.wiley.TeamProject.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long questionId;
    private String selectedOption; // A/B/C/D
}

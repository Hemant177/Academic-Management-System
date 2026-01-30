package com.wiley.TeamProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionResponseDTO {

    private Long questionId;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}

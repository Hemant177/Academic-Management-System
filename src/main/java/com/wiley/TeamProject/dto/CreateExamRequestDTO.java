package com.wiley.TeamProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateExamRequestDTO {
    private Long courseId;
    private List<CreateQuestionDTO> questions;
}


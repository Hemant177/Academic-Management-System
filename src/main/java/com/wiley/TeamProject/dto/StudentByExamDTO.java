package com.wiley.TeamProject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentByExamDTO {
    private Long student_id;
    private String student_name;
    private Integer marksObtained;
    private boolean pass;
}

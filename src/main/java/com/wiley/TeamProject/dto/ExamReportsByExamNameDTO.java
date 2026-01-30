package com.wiley.TeamProject.dto;


import com.wiley.TeamProject.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamReportsByExamNameDTO {
    private Exam exam;

    private String course_name;
    private Integer total_students_count;
    private Integer attended_count;
    private Integer absentees_count;

    private Integer passPercentage;
    private Integer minScore;
    private Integer maxScore;
    private Integer avgScore;

    private List<StudentByExamDTO> studentDtoList;
    private List<AbsenteesDTO> absentees;

}

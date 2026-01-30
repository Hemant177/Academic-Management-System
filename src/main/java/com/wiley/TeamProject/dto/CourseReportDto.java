package com.wiley.TeamProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseReportDto {
    private Long courseId;
    private String courseCode;
    private String courseName;
    private String courseDescription;
}

package com.wiley.TeamProject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class StudentReportDTO {

    private Long studentId;
    private String studentCode;
    private String studentName;
    private String email;
    private String phone;
    private String gender;
    private LocalDate dob;
    private String status;
    private String preferredLocation;

    private long coursesCount;
    private List<String> courseNames;

    private long examsAttendedCount;
    private long examsPassedCount;

    private Double avgScore;


}
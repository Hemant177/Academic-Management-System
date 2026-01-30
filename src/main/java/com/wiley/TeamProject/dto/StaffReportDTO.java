package com.wiley.TeamProject.dto;

public interface StaffReportDTO {
    Long getStaffId();
    String getStaffCode();
    String getStaffName();
    String getCourseName();
    String getLocationName();
    Integer getStudentCount(); // Must match "AS studentCount" in SQL
}
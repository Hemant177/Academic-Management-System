package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.StudentEnrollment;
import java.util.List;

public interface EnrollmentService {
    StudentEnrollment enroll(Long studentId, Long courseId, Long intakeId);
    List<StudentEnrollment> getAll();
}

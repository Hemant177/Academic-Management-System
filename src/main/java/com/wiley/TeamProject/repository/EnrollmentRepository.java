package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Intake;
import com.wiley.TeamProject.entity.Student;
import com.wiley.TeamProject.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

    // Check if student already enrolled in same intake
    boolean existsByStudentAndIntake(Student student, Intake intake);

    // Count how many students are enrolled in an intake
    long countByIntake(Intake intake);

    void deleteByStudentStudentId(Long studentId);
}

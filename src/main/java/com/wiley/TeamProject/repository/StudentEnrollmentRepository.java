package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Course;
import com.wiley.TeamProject.entity.Student;
import com.wiley.TeamProject.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository
        extends JpaRepository<StudentEnrollment, Long> {

    boolean existsByStudentAndCourse(Student student, Course course);
}

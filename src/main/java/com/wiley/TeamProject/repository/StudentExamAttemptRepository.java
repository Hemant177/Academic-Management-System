package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Student;
import com.wiley.TeamProject.entity.Course;
import com.wiley.TeamProject.entity.StudentExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentExamAttemptRepository
        extends JpaRepository<StudentExamAttempt, Long> {

    boolean existsByStudentAndCourse(Student student, Course course);

    Optional<StudentExamAttempt> findByStudentAndCourse(Student student, Course course);
}

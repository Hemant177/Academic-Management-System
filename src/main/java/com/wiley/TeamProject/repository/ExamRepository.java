package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Exam;
import com.wiley.TeamProject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> findByCourseAndStatus(Course course, String status);
}

package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Exam;
import com.wiley.TeamProject.entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {

    List<ExamQuestion> findByExam(Exam exam);
}

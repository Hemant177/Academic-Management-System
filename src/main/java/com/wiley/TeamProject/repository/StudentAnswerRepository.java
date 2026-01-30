package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAnswerRepository
        extends JpaRepository<StudentAnswer, Long> {
}

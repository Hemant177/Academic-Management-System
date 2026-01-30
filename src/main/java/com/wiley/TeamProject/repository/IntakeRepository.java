package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Intake;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IntakeRepository extends JpaRepository<Intake, Long> {
    List<Intake> findByCourseId(Long courseId);
    long countByStatus(String status);

    boolean existsByCourseIdAndIntakeName(Long courseId, String intakeName);
}
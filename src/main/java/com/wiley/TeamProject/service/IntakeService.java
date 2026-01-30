package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Intake;

import java.util.List;

public interface IntakeService {
    Intake createIntake(Intake intake);
    List<Intake> getByCourse(Long courseId);
    long countIntakes();

    List<Intake> getAll();

    boolean existsByCourseIdAndIntakeName(Long courseId, String intakeName);
}
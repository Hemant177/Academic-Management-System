package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Intake;
import com.wiley.TeamProject.repository.IntakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class IntakeServiceImpl implements IntakeService {

    private final IntakeRepository repo;

    @Override
    public Intake createIntake(Intake intake) {

        // Duplicate check
        if (repo.existsByCourseIdAndIntakeName(intake.getCourseId(), intake.getIntakeName())) {
            throw new RuntimeException("This intake name already exists for this course.");
        }

        intake.setAvailableSeats(intake.getTotalSeats());
        intake.setStatus("OPEN");
        return repo.save(intake);
    }

    @Override
    public List<Intake> getByCourse(Long courseId) {
        return repo.findByCourseId(courseId);
    }

    @Override
    public long countIntakes() {
        return repo.countByStatus("OPEN");
    }

    @Override
    public List<Intake> getAll() {
        return repo.findAll();
    }

    @Override
    public boolean existsByCourseIdAndIntakeName(Long courseId, String intakeName) {
        return repo.existsByCourseIdAndIntakeName(courseId, intakeName);
    }
}
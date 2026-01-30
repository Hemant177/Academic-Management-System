package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.*;
import com.wiley.TeamProject.exception.EnrollmentException;
import com.wiley.TeamProject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final IntakeRepository intakeRepo;
    private final LocationRepository locationRepo;

    @Override
    public StudentEnrollment enroll(Long studentId, Long courseId, Long intakeId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new EnrollmentException("Student not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new EnrollmentException("Course not found"));

        Intake intake = intakeRepo.findById(intakeId)
                .orElseThrow(() -> new EnrollmentException("Intake not found"));

        // 🔴 CHECK 1: Student already enrolled
        if (enrollmentRepo.existsByStudentAndIntake(student, intake)) {
            throw new EnrollmentException("Student is already present in this intake");
        }

        // 🔴 CHECK 2: No available seats
        if (intake.getAvailableSeats() <= 0) {
            throw new EnrollmentException("Seats are filled for this intake");
        }

        // 🟢 UPDATE SEATS
        intake.setAvailableSeats(intake.getAvailableSeats() - 1);

        // 🟢 CLOSE INTAKE IF NOW ZERO
        if (intake.getAvailableSeats() == 0) {
            intake.setStatus("CLOSED");
        }

        intakeRepo.save(intake);   // Save seat update

        // 🟢 CREATE ENROLLMENT RECORD
        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setIntake(intake);
        enrollment.setEnrollmentDate(new Date(System.currentTimeMillis()));
        enrollment.setEnrollmentStatus("Enrolled");

        return enrollmentRepo.save(enrollment);
    }


    @Override
    public List<StudentEnrollment> getAll() {
        return enrollmentRepo.findAll();
    }
}

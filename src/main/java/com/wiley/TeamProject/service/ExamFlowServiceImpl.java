package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.QuestionResponseDTO;
import com.wiley.TeamProject.dto.StartExamResponse;
import com.wiley.TeamProject.entity.*;
import com.wiley.TeamProject.exception.AccessDeniedException;
import com.wiley.TeamProject.exception.ResourceNotFoundException;
import com.wiley.TeamProject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamFlowServiceImpl implements ExamFlowService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentEnrollmentRepository enrollmentRepository;
    private final ExamRepository examRepository;
    private final ExamQuestionRepository questionRepository;
    private final StudentExamAttemptRepository attemptRepository;

    @Override
    public StartExamResponse startExam(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        boolean enrolled =
                enrollmentRepository.existsByStudentAndCourse(student, course);

        if (!enrolled) {
            throw new AccessDeniedException("Student not enrolled in this course");
        }

        boolean attempted =
                attemptRepository.existsByStudentAndCourse(student, course);

        if (attempted) {
            throw new AccessDeniedException("Exam already attempted");
        }

        Exam exam = examRepository.findByCourseAndStatus(course, "ACTIVE")
                .orElseThrow(() -> new ResourceNotFoundException("No active exam for this course"));

        List<ExamQuestion> questions = questionRepository.findByExam(exam);

        if (questions.size() != 20) {
            throw new ResourceNotFoundException("Exam questions not configured properly");
        }

        List<QuestionResponseDTO> response =
                questions.stream()
                        .map(q -> new QuestionResponseDTO(
                                q.getQuestionId(),
                                q.getQuestionText(),
                                q.getOptionA(),
                                q.getOptionB(),
                                q.getOptionC(),
                                q.getOptionD()
                        ))
                        .toList();

        return new StartExamResponse(
                exam.getExamId(),
                exam.getDurationMinutes(),
                response
        );
    }
}

package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.*;
import com.wiley.TeamProject.entity.*;
import com.wiley.TeamProject.exception.AccessDeniedException;
import com.wiley.TeamProject.exception.ResourceNotFoundException;
import com.wiley.TeamProject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamEvaluationServiceImpl implements ExamEvaluationService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ExamRepository examRepository;
    private final ExamQuestionRepository questionRepository;
    private final StudentEnrollmentRepository enrollmentRepository;
    private final StudentExamAttemptRepository attemptRepository;
    private final StudentAnswerRepository answerRepository;

    @Override
    public ExamResultResponse submitExam(Long examId, SubmitExamRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if (!enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new AccessDeniedException("Student not enrolled in this course");
        }

        if (attemptRepository.existsByStudentAndCourse(student, course)) {
            throw new AccessDeniedException("Exam already attempted");
        }

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found"));

        List<ExamQuestion> questions = questionRepository.findByExam(exam);

        Map<Long, String> correctMap =
                questions.stream().collect(Collectors.toMap(
                        ExamQuestion::getQuestionId,
                        ExamQuestion::getCorrectOption
                ));

        int score = 0;

        for (AnswerDTO ans : request.getAnswers()) {
            String correct = correctMap.get(ans.getQuestionId());
            if (correct == null) continue;

            if (correct.equalsIgnoreCase(ans.getSelectedOption())) {
                score += 4;
            } else {
                score -= 1;
            }
        }

        if (score < 0) score = 0;

        String resultStatus = score >= 50 ? "PASS" : "FAIL";

        StudentExamAttempt attempt = new StudentExamAttempt();
        attempt.setStudent(student);
        attempt.setCourse(course);
        attempt.setExam(exam);
        attempt.setScore(score);
        attempt.setResultStatus(resultStatus);

        attempt = attemptRepository.save(attempt);

        for (AnswerDTO ans : request.getAnswers()) {
            StudentAnswer answer = new StudentAnswer();
            answer.setAttempt(attempt);
            answer.setQuestion(
                    questions.stream()
                            .filter(q -> q.getQuestionId().equals(ans.getQuestionId()))
                            .findFirst()
                            .orElseThrow()
            );
            answer.setSelectedOption(ans.getSelectedOption());
            answerRepository.save(answer);
        }

        return new ExamResultResponse(
                student.getFullName(),
                course.getCourseName(),
                score,
                resultStatus
        );
    }
}

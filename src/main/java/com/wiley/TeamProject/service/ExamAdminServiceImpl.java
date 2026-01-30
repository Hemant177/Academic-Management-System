package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.CreateExamRequestDTO;
import com.wiley.TeamProject.dto.CreateQuestionDTO;
import com.wiley.TeamProject.entity.Course;
import com.wiley.TeamProject.entity.Exam;
import com.wiley.TeamProject.entity.ExamQuestion;
import com.wiley.TeamProject.exception.ResourceNotFoundException;
import com.wiley.TeamProject.repository.CourseRepository;
import com.wiley.TeamProject.repository.ExamQuestionRepository;
import com.wiley.TeamProject.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExamAdminServiceImpl implements ExamAdminService {

    private final CourseRepository courseRepository;
    private final ExamRepository examRepository;
    private final ExamQuestionRepository examQuestionRepository;

    @Override
    @Transactional
    public void createExam(CreateExamRequestDTO request) {

        // 1. Validate course
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // 2. Validate question count
        if (request.getQuestions() == null || request.getQuestions().size() != 20) {
            throw new IllegalArgumentException("Exactly 20 questions are required");
        }

        // 3. Create exam
        Exam exam = new Exam();
        exam.setCourse(course);
        exam.setStatus("ACTIVE");
        // totalQuestions, totalMarks, durationMinutes use defaults

        exam = examRepository.save(exam);

        // 4. Save questions
        for (CreateQuestionDTO q : request.getQuestions()) {

            ExamQuestion question = new ExamQuestion();
            question.setExam(exam);
            question.setQuestionText(q.getQuestionText());
            question.setOptionA(q.getOptionA());
            question.setOptionB(q.getOptionB());
            question.setOptionC(q.getOptionC());
            question.setOptionD(q.getOptionD());
            question.setCorrectOption(q.getCorrectOption());

            examQuestionRepository.save(question);
        }
    }
}

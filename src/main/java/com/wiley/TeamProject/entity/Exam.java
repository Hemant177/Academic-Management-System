package com.wiley.TeamProject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exams")
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long examId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "total_questions", nullable = false)
    private Integer totalQuestions = 20;

    @Column(name = "total_marks", nullable = false)
    private Integer totalMarks = 100;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes = 20;

    @Column(name = "status", nullable = false)
    private String status = "ACTIVE";
}

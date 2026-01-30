package com.wiley.TeamProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "student_exam_attempts",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"student_id", "course_id"}
        )
)
@Data
public class StudentExamAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attempt_id")
    private Long attemptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @Column(name = "score", nullable = false)
    private Integer score;   // out of 100

    @Column(name = "result_status", nullable = false)
    private String resultStatus; // PASS / FAIL

    @Column(name = "attempted_at", nullable = false)
    private LocalDateTime attemptedAt = LocalDateTime.now();
}

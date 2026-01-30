package com.wiley.TeamProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "student_enrollment")
public class StudentEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "intake_id")
    private Intake intake;

    private Date enrollmentDate;

    private String enrollmentStatus = "Enrolled";
}

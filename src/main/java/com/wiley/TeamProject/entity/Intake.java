package com.wiley.TeamProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "intake")
public class Intake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long intakeId;

    private Long courseId;
    private String intakeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalSeats;
    private Integer availableSeats;
    private String status;

}
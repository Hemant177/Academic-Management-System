package com.wiley.TeamProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffId;

    @Column(unique = true)
    private String staffCode;

    private String staffName;
    private String email;
//    private String role;
    @Column(nullable = false)
    private String role = "STAFF";

    private Integer maxCoursesAllowed;
    private String status;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    private List<Course> courses;

}
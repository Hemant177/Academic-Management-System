//package com.wiley.TeamProject.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.sql.Date;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "student")
//public class Student {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long studentId;
//
//    @Column(unique = true)
//    private String studentCode;
//
//    private String fullName;
//
//    @Column(unique = true)
//    private String email;
//
//    private String phone;
//
//    private String gender;
//
//    private Date dob;
//
//    // ✅ NEW
//    private String password;
//
//    // ✅ NEW
//    private String status = "ACTIVE";
//}

package com.wiley.TeamProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank(message = "Student code is required")
    @Pattern(regexp = "ST\\d{3}", message = "Student code must be like ST101")
    @Column(unique = true, nullable = false)
    private String studentCode;

    @NotBlank(message = "Full name is required")
    @Size(min = 3, max = 50, message = "Full name must be 3–50 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private Date dob;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank
    private String status = "ACTIVE";
}

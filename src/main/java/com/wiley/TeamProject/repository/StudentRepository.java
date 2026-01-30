package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByStudentCode(String studentCode);
    long count();

}

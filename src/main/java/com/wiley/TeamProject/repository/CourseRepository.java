package com.wiley.TeamProject.repository;


import com.wiley.TeamProject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String code);
    List<Course> findByStatus(String status);
    long countByStatus(String status);

}







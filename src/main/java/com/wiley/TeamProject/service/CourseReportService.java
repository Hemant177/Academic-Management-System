package com.wiley.TeamProject.service;//package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Course;
import com.wiley.TeamProject.repository.CourseReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseReportService {
    final CourseReportRepository repository;
    public List<Course> getAllCourses(){
        return  repository.findAll();
    }

    public Course getCourseById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public Optional<Course> getCourseByCode(String code) {
        return repository.findByCourseCode(code);
    }
}

package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);
    List<Course> getActiveCourses();
    void disableCourse(Long id);
    void deleteCourse(Long id);
    Course updateCourse(Long id, Course newCourse);
    long getTotalActiveCourses();
    Course assignStaffToCourse(Long courseId, Long staffId);
    Course assignLocationToCourse(Long courseId, Long locationId);
}

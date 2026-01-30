package com.wiley.TeamProject.controller;


import com.wiley.TeamProject.entity.Course;
import com.wiley.TeamProject.service.CourseService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return service.createCourse(course);
    }

    @GetMapping
    public List<Course> getAll() {
        return service.getActiveCourses();
    }

    @PutMapping("/{id}/disable")
    public String disable(@PathVariable Long id) {
        service.disableCourse(id);
        return "Course Disabled";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCourse(id);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course course) {
        return service.updateCourse(id, course);
    }

    @GetMapping("/count")
    public long getCourseCount() {
        return service.getTotalActiveCourses();
    }

    @PutMapping("/{courseId}/assign-staff/{staffId}")
    public Course assignStaff(
            @PathVariable Long courseId,
            @PathVariable Long staffId
    ) {
        return service.assignStaffToCourse(courseId, staffId);
    }
}

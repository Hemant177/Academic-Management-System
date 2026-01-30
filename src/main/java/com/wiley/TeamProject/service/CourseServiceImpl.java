package com.wiley.TeamProject.service;


import com.wiley.TeamProject.entity.Course;
import com.wiley.TeamProject.entity.Location;
import com.wiley.TeamProject.entity.Staff;
import com.wiley.TeamProject.repository.CourseRepository;
import com.wiley.TeamProject.repository.LocationRepository;
import com.wiley.TeamProject.repository.StaffRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;
    private final StaffRepository staffRepo;
    private final LocationRepository locationRepo;

    public CourseServiceImpl(CourseRepository repo, StaffRepository staffrepo, LocationRepository locationRepo) {
        this.repo = repo;
        this.staffRepo = staffrepo;
        this.locationRepo = locationRepo;
    }

    public Course createCourse(Course course) {
        repo.findByCourseCode(course.getCourseCode())
                .ifPresent(c -> { throw new RuntimeException("Course exists"); });

        course.setStatus("ACTIVE");
        course.setCreatedAt(LocalDateTime.now());
        return repo.save(course);
    }

    public List<Course> getActiveCourses() {
        return repo.findByStatus("ACTIVE");
    }

    public void disableCourse(Long id) {
        Course c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        c.setStatus("INACTIVE");
        repo.save(c);
    }

    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }

    public Course updateCourse(Long id, Course newCourse) {
        Course existing = repo.findById(id).orElseThrow();
        existing.setCourseName(newCourse.getCourseName());
        existing.setCourseLevel(newCourse.getCourseLevel());
        existing.setDurationMonths(newCourse.getDurationMonths());
        return repo.save(existing);
    }

    public long getTotalActiveCourses() {
        return repo.countByStatus("ACTIVE");
    }

    public Course assignStaffToCourse(Long courseId, Long staffId)
    {

    Course course = repo.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));

    Staff staff = staffRepo.findById(staffId)
            .orElseThrow(() -> new RuntimeException("Staff not found"));

    int assignedCourses = staff.getCourses() == null ? 0 : staff.getCourses().size();

    if (assignedCourses >= staff.getMaxCoursesAllowed()) {
        throw new RuntimeException(
                "Staff has already reached maximum allowed courses: "
                        + staff.getMaxCoursesAllowed()
        );
    }

    course.setStaff(staff);
    return repo.save(course);
    }

    public Course assignLocationToCourse(Long courseId, Long locationId) {

        Course course = repo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Location location = locationRepo.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        course.setLocation(location);
        return repo.save(course);
    }

}

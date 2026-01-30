package com.wiley.TeamProject.repository;//package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseReportRepository extends JpaRepository<Course, Long> {

    //=============================ANKITA========================
    @Query("SELECT c.courseId, c.courseName FROM Course c")
    List<Object[]> findAllCourses();


    //=============================SRIDHAR========================
    @Query("SELECT c FROM Course c WHERE c.courseCode = :courseCode")
    Optional<Course> findByCourseCode(@Param("courseCode") String courseCode);

}

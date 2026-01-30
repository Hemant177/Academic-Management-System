package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Student;
import com.wiley.TeamProject.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentEnrollmentReportRepository extends JpaRepository<StudentEnrollment,Long> {

    @Query("SELECT se.student FROM StudentEnrollment se WHERE se.course.courseCode = :courseCode")
    List<Student> findStudentsByCourseCode(@Param("courseCode") String courseCode);

    //=============================ANKITA========================
    @Query("Select distinct se.course.courseName from StudentEnrollment se where se.student.studentId=:studentId")
    List<String> findCourseNamesByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT e.student FROM StudentEnrollment e WHERE (:courseId IS NULL OR e.course.courseId = :courseId) AND (:studentCode IS NULL OR e.student.studentCode = :studentCode)")
    List<Student> findByCourseAndCode(@Param("courseId") Long courseId, @Param("studentCode") String studentCode);

    @Query("SELECT distinct se.course.location.locationName FROM StudentEnrollment se WHERE se.student.studentId = :studentId")
    List<String> findLocationByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT e.student FROM StudentEnrollment e WHERE e.course.courseId = :courseId")
    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
}
package com.wiley.TeamProject.repository;//package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamReportRepository extends JpaRepository<Exam, Long> {

    @Query("SELECT e FROM Exam e WHERE e.course.courseId = :courseId")
    List<Exam> findExamsByCourseId(@Param("courseId") Long courseId);

//    //=================================== ANKITA ===========================================
//
//    @Query("select e from Exam e where e.course IN (select c from Student s join s.courses c where s.studentId =:studentId) and NOT exists (Select 1 from ExamResult m where m.exam = e and m.student.student_id =:studentId)")
//    List<Exam> findAbsentExamsByStudent(@Param("studentId") Long studentId);
//
//    @Query("select COUNT(e) from Exam e where e.course.courseId = :courseId")
//    long countByCourseId(@Param("courseId") Long courseId);
//
//
    //=================================== ANKITA ===========================================

    @Query("select COUNT(e) from Exam e where e.course.courseId = :courseId")
    long countByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT e FROM Exam e WHERE e.course.courseId IN (SELECT en.course.courseId FROM StudentEnrollment en WHERE en.student.studentId = :studentId) AND e.examId NOT IN (SELECT a.exam.examId FROM StudentExamAttempt a WHERE a.student.studentId = :studentId)")
    List<Exam> findAbsentExamsByStudent(@Param("studentId") Long studentId);

    //=================================== PAVAN ===========================================

    @Query("SELECT e FROM Exam e WHERE LOWER(e.course.courseName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Exam> findByExam_name(@Param("name") String examName);

//    @Query(value = "SELECT * FROM exam WHERE course_id = :courseId", nativeQuery = true)
//    List<Exam> findExamsByCourseId(@Param("courseId") Long courseId);



}
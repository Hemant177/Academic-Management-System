package com.wiley.TeamProject.repository;


import com.wiley.TeamProject.entity.StudentExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentExamAttemptReportRepository
        extends JpaRepository<StudentExamAttempt, Long> {

    @Query("Select count(sea) from StudentExamAttempt sea where sea.student.studentId=:studentId and sea.resultStatus='PASS'")
    Long getPassCountByStudentId(@Param("studentId") Long studentId);

    @Query("Select count(sea) from StudentExamAttempt sea where sea.student.studentId=:studentId")
    Long getExamsAttendedCount(@Param("studentId") Long studentId);

    @Query(" SELECT COALESCE(AVG(sea.score), 0) FROM StudentExamAttempt sea WHERE sea.student.studentId = :studentId")
    Double getAverageScoreByStudentId(@Param("studentId") Long studentId);


    @Query("SELECT a FROM StudentExamAttempt a WHERE a.student.studentId = :studentId")
    List<StudentExamAttempt> findAttemptsByStudentId(@Param("studentId") Long studentId);


}
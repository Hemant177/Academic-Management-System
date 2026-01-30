package com.wiley.TeamProject.repository;//package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.dto.AbsenteesDTO;
import com.wiley.TeamProject.entity.StudentExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarksReportReposiotry extends JpaRepository<StudentExamAttempt,Long> {


    //=================================== PAVAN ===========================================

    @Query(value = "SELECT COUNT(*) FROM student_exam_attempts er WHERE er.exam_id = :examId",nativeQuery = true)
    Integer countAttended(@Param("examId") Long examId);

    @Query(value = "select COALESCE(min(score),0) from student_exam_attempts where exam_id=:examId",nativeQuery = true)
    Integer getMinScore(@Param("examId") Long examId);

    @Query(value = "select COALESCE(max(score),0) from student_exam_attempts where exam_id=:examId",nativeQuery = true)
    Integer getMaxScore(@Param("examId") Long examId);

    @Query(value = "select COALESCE(avg(score),0) from student_exam_attempts where exam_id=:examId",nativeQuery = true)
    Double getAvgScore(@Param("examId") Long examId);

    @Query(value = "select count(*) from student_exam_attempts where exam_id=:examId and score>=40",nativeQuery = true)
    Integer countPassed(@Param("examId") Long examId);


    @Query(value = "select s.student_id,s.full_name,er.score " +
            "from student_exam_attempts er " +
            "join student s on er.student_id= s.student_id " +
            "where er.exam_id=:examId " +
            "order by er.score desc",nativeQuery = true)
    List<Object[]> getAllStudentsByExamId(@Param("examId") Long examId);


//    @Query(value = "SELECT s.student_id, s.full_name " +
//            "FROM student s " +
//            "JOIN course_student cs ON s.student_id = cs.student_id " +
//            "JOIN exam e ON cs.course_id = e.course_id " +
//            "LEFT JOIN student_exam_attempts er  " +
//            "    ON er.student_id = s.student_id  " +
//            "    AND er.exam_id = :examId " +
//            "WHERE e.exam_id = :examId " +
//            "AND er.student_id IS NULL;",nativeQuery = true)
//    List<AbsenteesDTO> getAbsentStudentsByExamId(@Param("examId") Long examId);


    @Query(value = """
    SELECT s.student_id, s.full_name
    FROM student s
    JOIN student_enrollment se 
        ON se.student_id = s.student_id
    JOIN exams e 
        ON e.course_id = se.course_id
    LEFT JOIN student_exam_attempts sea
        ON sea.student_id = s.student_id
        AND sea.exam_id = :examId
    WHERE e.exam_id = :examId
      AND sea.attempt_id IS NULL
""", nativeQuery = true)
    List<AbsenteesDTO> getAbsentStudentsByExamId(@Param("examId") Long examId);



}

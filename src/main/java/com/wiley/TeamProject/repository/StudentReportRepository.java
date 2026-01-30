package com.wiley.TeamProject.repository;//package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentReportRepository extends JpaRepository<Student, Long> {

    //=================================== ANKITA ===========================================

//    @Query("Select s from Student s where LOWER(s.full_name) like LOWER(CONCAT('%',:query,'%')) OR s.student_code = :query OR CAST(s.student_id AS string) = :query")
//    List<Student> searchStudents(@Param("query") String query);
////
////    @Query("SELECT s FROM Student s JOIN s.courses c WHERE (:courseId IS NULL OR c.course_id = :courseId) AND (:studentCode IS NULL OR s.student_code = :studentCode)")
////    List<Student> findByCourseAndCode(@Param("courseId") Long courseId, @Param("studentCode") String studentCode);
////
////    @Query("select s from Student s join s.courses c where c.course_id  = :courseId")
////    List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);
////
//    @Query("SELECT s FROM Student s WHERE s.student_code = :studentCode")
//    Student findByCode(@Param("studentCode") String studentCode);

//=================================== ANKITA ===========================================

@Query("Select s from Student s where LOWER(s.fullName) like LOWER(CONCAT('%',:query,'%')) OR s.studentCode = :query OR CAST(s.studentId AS string) = :query")
List<Student> searchStudents(@Param("query") String query);

    @Query("SELECT s FROM Student s WHERE s.studentCode = :studentCode")
    Student findByCode(@Param("studentCode") String studentCode);
    @Query("SELECT COUNT(s) FROM Student s WHERE s.status = 'ACTIVE'")
    long countActiveStudents();

    @Query("SELECT COUNT(s) FROM Student s WHERE s.status = 'SUSPENDED'")
    long countSuspendedStudents();

    //=================================== PAVAN ===========================================

    @Query(value = "SELECT COUNT(*) FROM student s join course_student cs on s.student_id=cs.student_id WHERE cs.course_id = :courseId",nativeQuery = true)
    Integer countStudentsInCourse(@Param("courseId") Long courseId);

}
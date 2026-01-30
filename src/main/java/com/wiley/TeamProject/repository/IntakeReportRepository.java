package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Intake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IntakeReportRepository extends JpaRepository<Intake,Long> {

    @Query("SELECT SUM(i.totalSeats - i.availableSeats) FROM Intake i WHERE i.courseId = :courseId")
    Integer getTotalEnrolledByCourse(@Param("courseId") Long courseId);

}

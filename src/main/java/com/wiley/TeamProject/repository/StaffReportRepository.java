package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.dto.*;
import com.wiley.TeamProject.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffReportRepository extends JpaRepository<Staff, Long> {

    // =========================
    // MAIN STAFF TABLE
    // =========================
    @Query(value = """
    SELECT
        s.staff_id   AS staffId,
        s.staff_code AS staffCode,
        s.staff_name AS staffName,
        GROUP_CONCAT(DISTINCT c.course_name ORDER BY c.course_name SEPARATOR ', ') AS courseName,
        l.location_name AS locationName,
        COUNT(st.student_id) AS studentCount
    FROM staff s
    JOIN courses c ON c.staff_id = s.staff_id
    LEFT JOIN student_enrollment st ON st.course_id = c.course_id
    LEFT JOIN location l ON c.location_id = l.location_id
    GROUP BY s.staff_id, s.staff_code, s.staff_name, l.location_name
    ORDER BY s.staff_id
""", nativeQuery = true)
    List<StaffReportDTO> fetchStaffTable();

    // =========================
    // STAFF OVERVIEW (CARDS)
    // =========================
    @Query(value = """
    SELECT
        s.staff_id   AS staffId,
        s.staff_code AS staffCode,
        s.staff_name AS staffName,
        MIN(l.location_name) AS locationName,  
        COUNT(DISTINCT c.course_id) AS totalCourses
    FROM staff s
    JOIN courses c ON c.staff_id = s.staff_id
    LEFT JOIN location l ON c.location_id = l.location_id
    GROUP BY s.staff_id, s.staff_code, s.staff_name
    ORDER BY s.staff_id
""", nativeQuery = true)
    List<StaffOverviewDTO> fetchStaffOverview();


    // =========================
    // STAFF DETAILS BY CODE
    // =========================
    @Query(value = """
    SELECT
        s.staff_id   AS staffId,
        s.staff_code AS staffCode,
        s.staff_name AS staffName,
        s.email      AS email,
        c.course_name AS courseName,
        l.location_name AS locationName,
        (
            SELECT COUNT(se.student_id)
            FROM student_enrollment se
            JOIN courses c2 ON se.course_id = c2.course_id
            WHERE c2.staff_id = s.staff_id
        ) AS studentCount
    FROM staff s
    JOIN courses c ON c.staff_id = s.staff_id

    LEFT JOIN location l ON c.location_id = l.location_id
    WHERE s.staff_code = :staffCode
""", nativeQuery = true)
    List<StaffDetailDTO> fetchStaffDetailsByCode(
            @Param("staffCode") String staffCode
    );


    // =========================
    // FILTER BY LOCATION
    // =========================
    @Query(value = """
    SELECT
        s.staff_code AS staffCode,
        s.staff_name AS staffName,
        l.location_name AS locationName,
        GROUP_CONCAT(DISTINCT c.course_name ORDER BY c.course_name SEPARATOR ', ')
            AS courseName
    FROM staff s
    JOIN courses c ON c.staff_id = s.staff_id
    JOIN location l ON c.location_id = l.location_id
    WHERE l.location_name = :location
    GROUP BY s.staff_code, s.staff_name, l.location_name
    ORDER BY s.staff_code
    """, nativeQuery = true)
    List<StaffReportDTO> fetchByLocation(@Param("location") String location);


    // =========================
    // FILTER BY COURSE
    // =========================
    @Query(value = """
SELECT
    s.staff_code AS staffCode,
    s.staff_name AS staffName,
    l.location_name AS locationName,
    GROUP_CONCAT(DISTINCT c.course_name ORDER BY c.course_name SEPARATOR ', ')
        AS courseName
FROM staff s
JOIN courses c ON c.staff_id = s.staff_id
JOIN location l ON c.location_id = l.location_id
WHERE c.course_name = :course
GROUP BY s.staff_code, s.staff_name, l.location_name
ORDER BY s.staff_code
""", nativeQuery = true)
    List<StaffReportDTO> fetchByCourse(@Param("course") String course);


    @Query(value = "SELECT DISTINCT location_name FROM location ORDER BY location_name", nativeQuery = true)
    List<String> fetchAllLocations();

    @Query(value = "SELECT DISTINCT course_name FROM courses ORDER BY course_name", nativeQuery = true)
    List<String> fetchAllCourses();

}


package com.wiley.TeamProject.repository;
import com.wiley.TeamProject.entity.StaffCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffCourseMappingRepository
        extends JpaRepository<StaffCourseMapping, Long> {

    long countByStaffId(Long staffId);
    boolean existsByStaffIdAndCourseId(Long staffId, Long courseId);
}
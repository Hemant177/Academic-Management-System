package com.wiley.TeamProject.repository;

import com.wiley.TeamProject.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    long count();
}

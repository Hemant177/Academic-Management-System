package com.wiley.TeamProject.service;


import com.wiley.TeamProject.entity.Staff;

import java.util.List;

public interface StaffService {
    Staff createStaff(Staff staff);
    List<Staff> getAllStaff();
    void deleteStaff(Long id);
    Staff updateStaff(Long id, Staff newStaff);
    long getTotalStaff();
}
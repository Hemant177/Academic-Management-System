package com.wiley.TeamProject.service;

import com.wiley.TeamProject.entity.Staff;
import com.wiley.TeamProject.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff createStaff(Staff staff) {
        staff.setStatus("ACTIVE");
        return staffRepository.save(staff);
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    public Staff updateStaff(Long id, Staff newStaff) {
        Staff existing = staffRepository.findById(id).orElseThrow();
        existing.setStaffName(newStaff.getStaffName());
        existing.setEmail(newStaff.getEmail());
        existing.setMaxCoursesAllowed(newStaff.getMaxCoursesAllowed());
        return staffRepository.save(existing);
    }

    public long getTotalStaff() {
        return staffRepository.count();
    }


}

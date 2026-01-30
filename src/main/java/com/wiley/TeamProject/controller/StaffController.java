package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.entity.Staff;
import com.wiley.TeamProject.service.StaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }


    @PostMapping
    public Staff createStaff(@RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @DeleteMapping("/{id}")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "Staff Deleted";
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        return staffService.updateStaff(id, staff);
    }

    @GetMapping("/count")
    public long getStaffCount() {
        return staffService.getTotalStaff();
    }

}
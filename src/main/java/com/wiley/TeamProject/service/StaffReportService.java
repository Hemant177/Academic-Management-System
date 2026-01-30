package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.*;
import com.wiley.TeamProject.repository.StaffReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffReportService {

    private final StaffReportRepository repository;

    public StaffReportService(StaffReportRepository repository) {
        this.repository = repository;
    }

    public List<StaffReportDTO> getStaffTable() {
        List<StaffReportDTO> reports = repository.fetchStaffTable();
        // Filter out any null entries
        return reports.stream().filter(java.util.Objects::nonNull).toList();
    }
    public List<StaffOverviewDTO> getStaffOverview() {
        return repository.fetchStaffOverview();
    }

    public List<StaffDetailDTO> getStaffDetailsByCode(String staffCode) {
        return repository.fetchStaffDetailsByCode(staffCode);
    }

    public List<StaffReportDTO> getByLocation(String location) {
        return repository.fetchByLocation(location);
    }

    public List<StaffReportDTO> getByCourse(String course) {
        return repository.fetchByCourse(course);
    }

    public List<String> getAllLocations() {
        return repository.fetchAllLocations();
    }

    public List<String> getAllCourses() {
        return repository.fetchAllCourses();
    }

}

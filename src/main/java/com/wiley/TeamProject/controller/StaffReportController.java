package com.wiley.TeamProject.controller;//package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.service.StaffReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StaffReportController {

    private final StaffReportService service;

    public StaffReportController(StaffReportService service) {
        this.service = service;
    }

    // MAIN PAGE
    @GetMapping("/staffReport")
    public String staffReport(Model model) {
        model.addAttribute("staffReports", service.getStaffTable());
        model.addAttribute("staffOverview", service.getStaffOverview());

        // for dropdowns
        model.addAttribute("locations", service.getAllLocations());
        model.addAttribute("courses", service.getAllCourses());

        return "staffReport";
    }


    // STAFF DETAILS (BY STAFF CODE)
    @GetMapping("/staff/details")
    public String staffDetails(
            @RequestParam String staffCode,
            Model model
    ) {
        model.addAttribute("details",
                service.getStaffDetailsByCode(staffCode));
        return "staffDetails";
    }

    // FILTER BY LOCATION
    @GetMapping("/staff/byLocation")
    public String byLocation(
            @RequestParam String location,
            Model model
    ) {
        model.addAttribute("staffReports",
                service.getByLocation(location));
        return "staffFiltered";
    }

    // FILTER BY COURSE
    @GetMapping("/staff/byCourse")
    public String byCourse(
            @RequestParam String course,
            Model model
    ) {
        model.addAttribute("staffReports",
                service.getByCourse(course));
        return "staffFiltered";
    }
}

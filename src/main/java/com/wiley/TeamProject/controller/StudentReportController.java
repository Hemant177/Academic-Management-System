package com.wiley.TeamProject.controller;
import com.wiley.TeamProject.dto.StudentReportDTO;
import com.wiley.TeamProject.service.StudentReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentReportController {

    private final StudentReportService studentReportService;

    public StudentReportController(StudentReportService studentReportService) {
        this.studentReportService = studentReportService;
    }

    @GetMapping("/studentReport")
    public String listStudents(@RequestParam(required = false) String search, Model model) {
        model.addAttribute("stats", studentReportService.getDashboardStats());
        List<StudentReportDTO> reports = studentReportService.getStudentsReports(search);
        model.addAttribute("reports", reports);
//        model.addAttribute("stats", studentReportService.getDashboardStats());
        model.addAttribute("searchQuery", search);
        return "studentReportList";
    }
}
package com.wiley.TeamProject.controller;//package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.dto.ExamReportDTO;
import com.wiley.TeamProject.service.ExamReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ExamReportController {

    private final ExamReportService examReportService;


    @GetMapping("/examReportByStudent")
    public String viewExamReport(
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) String studentCode,
            Model model) {

        List<ExamReportDTO> reports = examReportService.getFilteredExamReports(courseId, studentCode);

        model.addAttribute("reports", reports);
        model.addAttribute("stats", examReportService.getDashboardStats(courseId, reports));

//        // dropdown
        model.addAttribute("courses", examReportService.getAllCourses());

        // to show in the input field
        model.addAttribute("selectedCourse", courseId);
        model.addAttribute("selectedStudent", studentCode);

        return "examReportByStudent";
    }
}
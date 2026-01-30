package com.wiley.TeamProject.controller;//package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.entity.Exam;
import com.wiley.TeamProject.repository.CourseReportRepository;
import com.wiley.TeamProject.service.ExamReportsByExamNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ExamReportsByExamNameController {

    @Autowired
    private ExamReportsByExamNameService service;

    @Autowired
    private CourseReportRepository courseRepo;


    @GetMapping("/examReportByExam")
    public String openSearchPage(
            @RequestParam(required = false) String examName,
            @RequestParam(required = false) Long courseId,
            Model model) {

        List<Exam> exams;

        if (courseId != null) {
            exams = service.getExamsByCourse(courseId);
            model.addAttribute("selectedCourseId", courseId);
        } else if (examName != null && !examName.isEmpty()) {
            exams = service.searchExamsByName(examName);
        } else {
            exams = service.getAllExams(); // default → show all
        }

        model.addAttribute("exams", exams);
        model.addAttribute("courses", courseRepo.findAll()); // for dropdown


        return "ExamReportsByExamName1";
    }



    @GetMapping("/examReportByExam/{examId}")
    public String viewExamReport(@PathVariable Long examId, Model model) {
        model.addAttribute("report", service.getExamReport(examId));
        System.out.println(service.getExamReport(examId));
        return "ExamReportsByExamName2";
    }
}

package com.wiley.TeamProject.controller;//package com.wiley.TeamProject.controller;

import com.wiley.TeamProject.entity.Course;
import com.wiley.TeamProject.repository.ExamReportRepository;
import com.wiley.TeamProject.repository.StudentEnrollmentReportRepository;
import com.wiley.TeamProject.service.CourseReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courseReport")
public class CourseReportController {
    public final CourseReportService courseService;

    public final StudentEnrollmentReportRepository sturepo;
    public final ExamReportRepository exrepo;

    @GetMapping
    public String getAllCourses(Model model){
        List<Course> courses=courseService.getAllCourses();
        model.addAttribute("courses",courses);
        return "course-list";
    }

//    @GetMapping(params = "courseCode")
//    public String redirectToCourse(@RequestParam String courseCode) {
//        return "redirect:/courseReport/" + courseCode;
//    }
//
//
//    @GetMapping("/{code}")
//    public String viewCourseById(@PathVariable String code, Model model){
//        Course course=courseService.getCourseByCode(code.toUpperCase());
//        model.addAttribute("course", course);
//        model.addAttribute("students",sturepo.findStudentsByCourseCode(code));
//        model.addAttribute("exams",exrepo.findExamsByCourseId(course.getCourseId()));
//
//
//        return "course-details";
//    }

    @GetMapping(params = "courseCode")
    public String redirectToCourse(@RequestParam String courseCode,Model model) {
        Optional<Course> course=courseService.getCourseByCode(courseCode.toUpperCase());
        if(course.isEmpty()){
            model.addAttribute("error","Course with code " + courseCode + " not found");
            model.addAttribute("courses",courseService.getAllCourses());
            return "course-list";
        }
        model.addAttribute("course", course.get());
        model.addAttribute("students",sturepo.findStudentsByCourseCode(courseCode));
        model.addAttribute("exams",exrepo.findExamsByCourseId(course.get().getCourseId()));
        return "course-details";
    }

    @GetMapping("/{code}")
    public String viewCourseById(@PathVariable String code, Model model){
        Optional<Course> course=courseService.getCourseByCode(code.toUpperCase());
        if(course.isPresent()) {
            model.addAttribute("course", course.get());
            model.addAttribute("students",sturepo.findStudentsByCourseCode(code));
            model.addAttribute("exams",exrepo.findExamsByCourseId(course.get().getCourseId()));
            return "course-details";
        }
        return "redirect:/courseReport";

    }
}

package com.wiley.TeamProject.service;//package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.StudentReportDTO;
import com.wiley.TeamProject.entity.Student;
import com.wiley.TeamProject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentReportService {

    private final StudentReportRepository studentReportRepository;
    private final StudentExamAttemptReportRepository studentExamAttemptReportRepository;
    private final CourseReportRepository courseReportRepository;
    private final StudentEnrollmentReportRepository studentEnrollmentReportRepository;


    // cards/stats
    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalStudents", studentReportRepository.count());
        stats.put("activeStudents", studentReportRepository.countActiveStudents());
        stats.put("suspendedStudents", studentReportRepository.countSuspendedStudents());
        return stats;
    }

    public List<StudentReportDTO> getStudentsReports(String query) {
        List<Student> students;
        if (query != null && !query.trim().isEmpty()){
            students = studentReportRepository.searchStudents(query.trim());
        }
        else{
            students = studentReportRepository.findAll();
        }
        return students.stream().map(this::convertToReportDTO).toList();
    }


    private StudentReportDTO convertToReportDTO(Student student) {

        Long studentId = student.getStudentId();
        Double avgScore = studentExamAttemptReportRepository.getAverageScoreByStudentId(studentId);
        double avg = (avgScore != null) ? avgScore : 0.0;
        List<String> courseNames = studentEnrollmentReportRepository.findCourseNamesByStudentId(studentId);
        int courseCount = courseNames.size();
        long passedCount = studentExamAttemptReportRepository.getPassCountByStudentId(studentId);
        long examsAttended = studentExamAttemptReportRepository.getExamsAttendedCount(student.getStudentId());
        List<String> locations = studentEnrollmentReportRepository.findLocationByStudentId(studentId);
        String locationString = locations.isEmpty() ? "N/A" : String.join(", ", locations);

        return StudentReportDTO.builder()
                .studentId(student.getStudentId())
                .studentCode(student.getStudentCode())
                .studentName(student.getFullName())
                .email(student.getEmail())
                .phone(student.getPhone())
                .gender(student.getGender())
                .dob(student.getDob()==null ? null : student.getDob().toLocalDate() )
                .status(student.getStatus())
                .preferredLocation(locationString)
                .coursesCount(courseCount)
                .courseNames(courseNames)
                .examsAttendedCount(examsAttended)
                .examsPassedCount(passedCount)
                .avgScore(avg)
                .build();
    }
}
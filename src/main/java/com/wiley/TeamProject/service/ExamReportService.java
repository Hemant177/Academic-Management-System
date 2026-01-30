package com.wiley.TeamProject.service;//package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.ExamReportDTO;
import com.wiley.TeamProject.entity.Exam;
import com.wiley.TeamProject.entity.Student;
import com.wiley.TeamProject.entity.StudentExamAttempt;
import com.wiley.TeamProject.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ExamReportService {

    private final StudentReportRepository studentReportRepository;
    private final ExamReportRepository examReportRepository;
    private final CourseReportRepository courseReportRepository;
    private final StudentEnrollmentReportRepository serpo;
    private final StudentExamAttemptReportRepository serapo;

    //
//
//    //list of object[], object[] is like a row and has cid, cname
    public List<Object[]> getAllCourses() {
        return courseReportRepository.findAllCourses();
    }

    public List<ExamReportDTO> getFilteredExamReports(Long courseId, String studentCode) {
        List<Student> students;

        String cleanCode = (studentCode != null && !studentCode.trim().isEmpty()) ? studentCode.trim() : null;

        if (courseId != null && cleanCode != null) {
            students = serpo.findByCourseAndCode(courseId, cleanCode);
        } else if (studentCode != null && !studentCode.trim().isEmpty()) {
            students = new ArrayList<>();
            Student s = studentReportRepository.findByCode(studentCode.trim());
            if (s != null) {
                students.add(s);
            }
        } else if (courseId != null) {
            students = serpo.findStudentsByCourseId(courseId);
        } else {
            students = studentReportRepository.findAll();
        }
        return students.stream().map(this::convertToExamReportDTO).toList();
    }

    //    cards/stats
    public Map<String, Object> getDashboardStats(Long courseId, List<ExamReportDTO> reports) {
        Map<String, Object> stats = new HashMap<>();
        if (reports == null || reports.isEmpty()) {
            stats.put("passRate", 0.0);
            stats.put("totalStudents", 0);
            stats.put("totalExams", 0L);
            return stats;
        }

        long totalAttended = 0;
        long totalPassed = 0;

        for (ExamReportDTO r : reports) {
            totalAttended += r.getTotalExamsAttended();
            for (ExamReportDTO.ExamDetailDTO detail : r.getAttendedExams()) {
                if (detail.isPassed()) {
                    totalPassed++;
                }
            }
        }

        long totalExamsHeld;
        if (reports.size() == 1) {
            totalExamsHeld = reports.get(0).getTotalExamsAttended() + reports.get(0).getAbsentExamsCount();
        } else if (courseId != null) {
            totalExamsHeld = examReportRepository.countByCourseId(courseId);
        } else {
            totalExamsHeld = examReportRepository.count();
        }

        stats.put("totalExams", totalExamsHeld);
        stats.put("passRate", totalAttended > 0 ? ((double) totalPassed / totalAttended) * 100 : 0.0);
        stats.put("totalStudents", reports.size());

        return stats;
    }

    private ExamReportDTO convertToExamReportDTO(Student student) {
        List<StudentExamAttempt> attempts = serapo.findAttemptsByStudentId(student.getStudentId());
        List<Exam> absentExams = examReportRepository.findAbsentExamsByStudent(student.getStudentId());


        int totalScore = 0;
        StudentExamAttempt maxAttempt = null;
        StudentExamAttempt minAttempt = null;

        List<ExamReportDTO.ExamDetailDTO> attendedDTOs = new ArrayList<>();


        for (StudentExamAttempt attempt : attempts) {
            totalScore += attempt.getScore();

            if (maxAttempt == null || attempt.getScore() > maxAttempt.getScore()) {
                maxAttempt = attempt;
            }
            if (minAttempt == null || attempt.getScore() < minAttempt.getScore()) {
                minAttempt = attempt;
            }

            String examDisplayName = attempt.getCourse().getCourseName();

            attendedDTOs.add(ExamReportDTO.ExamDetailDTO.builder()
                    .examId(attempt.getExam().getExamId())
                    .examName(examDisplayName)
                    .score(attempt.getScore())
                    .outOf(attempt.getExam().getTotalMarks())
                    .passed("PASS".equalsIgnoreCase(attempt.getResultStatus()))
                    .date(attempt.getAttemptedAt().toLocalDate())
                    .build());
        }

        double avgScore = (attempts.size() > 0) ? (double) totalScore / attempts.size() : 0.0;

        List<ExamReportDTO.AbsentExamDTO> absentDTOs = new ArrayList<>();
        for (Exam e : absentExams) {
            absentDTOs.add(ExamReportDTO.AbsentExamDTO.builder()
                    .examName(e.getCourse().getCourseName())
                    .build());
        }

        return ExamReportDTO.builder()
                .studentCode(student.getStudentCode())
                .studentName(student.getFullName())
                .totalExamsAttended((long) attempts.size())
                .absentExamsCount(absentExams.size())
                .attendedExams(attendedDTOs)
                .absentExams(absentDTOs)
                .avgScore(avgScore)
                .maxScore(maxAttempt != null ? maxAttempt.getScore() : 0)
                .maxScoreTestName(maxAttempt != null ? maxAttempt.getCourse().getCourseName() : "N/A")
                .minScore(minAttempt != null ? minAttempt.getScore() : 0)
                .minScoreTestName(minAttempt != null ? minAttempt.getCourse().getCourseName() : "N/A")
                .build();
    }
}
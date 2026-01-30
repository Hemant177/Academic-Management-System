package com.wiley.TeamProject.service;//package com.wiley.TeamProject.service;

import com.wiley.TeamProject.dto.AbsenteesDTO;
import com.wiley.TeamProject.dto.ExamReportsByExamNameDTO;
import com.wiley.TeamProject.dto.StudentByExamDTO;
import com.wiley.TeamProject.entity.Exam;
import com.wiley.TeamProject.repository.ExamReportRepository;
import com.wiley.TeamProject.repository.IntakeReportRepository;
import com.wiley.TeamProject.repository.MarksReportReposiotry;
import com.wiley.TeamProject.repository.StudentReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamReportsByExamNameService {

    @Autowired
    private ExamReportRepository examRepo;
    @Autowired
    private MarksReportReposiotry marksRepo;
    @Autowired
    private StudentReportRepository studentRepo;

    @Autowired
    private IntakeReportRepository intakeRepo;

    public List<Exam> searchExamsByName(String examName) {

        return examRepo.findByExam_name(examName);
    }

    public List<Exam> getAllExams() {
        return examRepo.findAll();
    }


    public ExamReportsByExamNameDTO  getExamReport(Long examId) {

        Exam exam = examRepo.findById(examId).orElse(null);

        Integer totalStudents = intakeRepo.getTotalEnrolledByCourse(exam.getCourse().getCourseId());
        Integer attended=marksRepo.countAttended(examId);
        Integer absentees=totalStudents-attended;

        Integer min = marksRepo.getMinScore(examId);
        Integer max=marksRepo.getMaxScore(examId);
        Integer avg=(int)Math.round(marksRepo.getAvgScore(examId));

        Integer passed=marksRepo.countPassed(examId);
        Integer passPercentage = (attended==0)?0:(passed*100/attended);

        List<Object[]> rows = marksRepo.getAllStudentsByExamId(examId);

        List<StudentByExamDTO> students = new ArrayList<>();

        for (Object[] row : rows) {

            StudentByExamDTO s = new StudentByExamDTO();
            s.setStudent_id(((Number) row[0]).longValue());
            s.setStudent_name((String)row[1]);

            // set other fields only if needed

            Integer score = ((Number) row[row.length - 1]).intValue();

            boolean pass = score >= 40;
            s.setMarksObtained(score);
            s.setPass(pass);

            students.add(s);

        }

        List<AbsenteesDTO> absenteesDTOList = marksRepo.getAbsentStudentsByExamId(examId);


        return new ExamReportsByExamNameDTO(
                exam,
                exam.getCourse().getCourseName(),
                totalStudents,
                attended,
                absentees,
                passPercentage,
                min,
                max,
                avg,
                students,
                absenteesDTOList
        );
    }


    public List<Exam> getExamsByCourse(Long courseId) {
        return examRepo.findExamsByCourseId(courseId);
    }

}

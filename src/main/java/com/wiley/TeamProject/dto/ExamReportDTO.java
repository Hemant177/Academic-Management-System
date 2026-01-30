package com.wiley.TeamProject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
@Builder
public class ExamReportDTO {
    private String studentCode;
    private String studentName;
    private long totalExamsAttended;
    private long absentExamsCount;

    @Builder.Default
    private List<ExamDetailDTO> attendedExams = Collections.emptyList();

    @Builder.Default
    private List<AbsentExamDTO> absentExams = Collections.emptyList();

    private Double avgScore;
    private Integer maxScore;
    private String maxScoreTestName;
    private Integer minScore;
    private String minScoreTestName;

    @Data
    @Builder
    public static class ExamDetailDTO {
        private Long examId;
        private String examName;
        private Integer score;
        private Integer outOf;
        private boolean passed;
        private LocalDate date;
    }

    @Data
    @Builder
    public static class AbsentExamDTO {
        private String examName;
        private LocalDate date;
    }
}
package ru.skypro.lessons.springboot.weblibrary1.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;

public interface ReportService {
    public int createReport();

    public ResponseEntity<Report>upload(int id);

    public String saveReportToFile(String reportJson);
}
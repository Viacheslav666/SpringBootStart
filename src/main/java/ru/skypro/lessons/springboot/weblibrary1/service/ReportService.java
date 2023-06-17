package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;

import java.io.IOException;

public interface ReportService {
    public int createReport() throws IOException;

    public ResponseEntity<Report>upload(int id);

    public String saveReportToFile(String reportJson);
}
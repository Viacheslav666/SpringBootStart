package ru.skypro.lessons.springboot.weblibrary1.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ReportService {
    public int createReport() throws IOException;

    public ResponseEntity<Resource> upload(int id);

    public String saveReportToFile(String reportJson);
    public ResponseEntity<Resource> getReportById(Integer id);
}
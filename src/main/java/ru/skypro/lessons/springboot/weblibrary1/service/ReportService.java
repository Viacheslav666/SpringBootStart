package ru.skypro.lessons.springboot.weblibrary1.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;

import java.util.List;

public interface ReportService {
    public int createReport();

    public ResponseEntity<Report>upload(int id);
}
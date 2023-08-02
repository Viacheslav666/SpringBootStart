package ru.skypro.lessons.springboot.weblibrary1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    public final ReportService  reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable int id) {
        return reportService.upload(id);
    }

}
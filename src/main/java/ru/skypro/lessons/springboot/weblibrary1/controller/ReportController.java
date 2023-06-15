package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
    public final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public int report() {
        return reportService.createReport();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable int id) {
        return reportService.upload(id);
    }
}
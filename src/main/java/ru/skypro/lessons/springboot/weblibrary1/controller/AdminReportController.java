package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;

import java.io.IOException;

@RestController
@RequestMapping("/admin/report")
public class AdminReportController {
    public final ReportService  reportService;

    public AdminReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @PostMapping
    public int report() throws IOException {
        return reportService.createReport();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable int id) {
        return reportService.upload(id);
    }
}
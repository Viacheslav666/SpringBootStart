package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportServiceImpl;

@RestController
@RequestMapping("/report")
public class ReportController {
    public final ReportServiceImpl reportServiceimpl;

    public ReportController(ReportServiceImpl reportServiceimpl) {
        this.reportServiceimpl = reportServiceimpl;

    }

    @PostMapping
    public int report() {
        return reportServiceimpl.createReport();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable int id) {
        return reportServiceimpl.upload(id);
    }
}
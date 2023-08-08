package ru.skypro.lessons.springboot.weblibrary1.controller;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Resource> upload(@PathVariable int id) {
        return reportService.upload(id);
    }

}
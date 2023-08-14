package ru.skypro.lessons.springboot.weblibrary1.controller;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {
    public final ReportService  reportService;
    private final EmployeeService employeeService;

    public ReportController(ReportService reportService, EmployeeService employeeService) {
        this.reportService = reportService;
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Resource> upload(@PathVariable int id) {
        return reportService.upload(id);
    }

}
package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> showEmployees() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("max")
    public int maxSalary1() {
        return employeeService.maxSalary();
    }

    @GetMapping("min")
    public int minSalary1() {
        return employeeService.minSalary();
    }

    @GetMapping("sum")
    public int sumSalary1() {
        return employeeService.sumSalary();
    }

    @GetMapping("high-salary")
    public List<Employee> highSalary() {
        return employeeService.highSalary();
    }
    }


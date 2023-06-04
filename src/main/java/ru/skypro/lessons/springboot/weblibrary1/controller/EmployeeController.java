package ru.skypro.lessons.springboot.weblibrary1.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public Employee postEmployee() {
        return employeeService.createEmployee(10,"Kirill", 5000);
    }

    @PutMapping("{id}")
    public void editeEmployee(@PathVariable int id) {
        employeeService.editEmployee(id);
    }
    @GetMapping("{id}")
    public List<Employee> getIdEmployee(@PathVariable int id) {
       return employeeService.getEmployee(id);
    }
    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deliteEmployee(id);
    }
    @GetMapping("{salary}")
    public List<Employee> salaryHigherThanEmployee(@PathParam("salaryHigherThan") @PathVariable int salary ) {
        return employeeService.getEmployee(salary);
    }



    @GetMapping("getAll")
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


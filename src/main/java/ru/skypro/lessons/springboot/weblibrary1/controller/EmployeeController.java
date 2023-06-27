package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.FullInfo;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")

public class EmployeeController {
    public final EmployeeService
            employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService =  employeeService;

    }

    @GetMapping("/withHighestSalary")
    public List<EmployeeDTO> withHighestSalary() {
        return employeeService.withHighestSalary();
    }

    @GetMapping
    public List<EmployeeDTO> employeesPosition(@RequestParam("position") Optional position) {
        if (position == null) {
            return employeeService.getAllEmployees();
        } else
            return employeeService.employeesPosition(position);
    }

    @GetMapping("{id}/fullInfo")
    public List<FullInfo> fullInfo(@PathVariable Integer id) {
        return employeeService.fullInfo(id);
    }
    @GetMapping( "/page")
    public List<EmployeeDTO> getEmployeeWithPaging(@RequestParam("page") int page) {
        return employeeService.getEmployeeWithPaging(page);
    }
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.upload(file); }

}


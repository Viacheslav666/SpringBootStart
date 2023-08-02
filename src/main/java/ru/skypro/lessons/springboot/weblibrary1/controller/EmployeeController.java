package ru.skypro.lessons.springboot.weblibrary1.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.FullInfo;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")


public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/withHighestSalary")
    public EmployeeDTO withHighestSalary() {

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

    @GetMapping("/page")
    public List<EmployeeDTO> getEmployeeWithPaging(@RequestParam("page") int page) {
        return employeeService.getEmployeeWithPaging(page);
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public void upload(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.upload(file);
    }

}


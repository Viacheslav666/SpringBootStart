package ru.skypro.lessons.springboot.weblibrary1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/withHighestSalary")
    public EmployeeDTO withHighestSalary() {
        return employeeService.withHighestSalary();
    }

    @GetMapping
    public List<EmployeeFullInfo> getEmployeeByPosition(@RequestParam("position") Integer position) {
        return employeeService.employeesPosition(position);
    }

    @GetMapping("/test")
    public ResponseEntity getString() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/fullInfo")
    public EmployeeFullInfo fullInfo(@PathVariable Integer id) {
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
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

}
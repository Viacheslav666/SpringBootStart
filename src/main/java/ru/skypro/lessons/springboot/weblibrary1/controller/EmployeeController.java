package ru.skypro.lessons.springboot.weblibrary1.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.FullInfo;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
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

}


package ru.skypro.lessons.springboot.weblibrary1.service;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    public final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.allEmployees();
    }

    @Override
    public List<Employee> showEmployee() {
        return null;
    }

    @Override
    public int sumSalary() {
        return employeeRepository.allEmployees().stream().
                mapToInt(Employee::getSalary).
                sum();
    }

    @Override
    public int minSalary() {
        return employeeRepository.allEmployees().stream().
                mapToInt(Employee::getSalary).
                min().
                orElse(0);
    }

    @Override
    public int maxSalary() {
        return employeeRepository.allEmployees().stream().
                mapToInt(Employee::getSalary).
                max().
                orElse(0);
    }

    @Override
    public List<Employee> highSalary() {
        int sum =  sumSalary();
        int sumEmployee = employeeRepository.allEmployees().size();
        int averageSalary = sum/sumEmployee;
        return(employeeRepository.allEmployees().stream().
                filter(employee -> employee.getSalary()>=averageSalary).
                collect(Collectors.toList()));
    }
}

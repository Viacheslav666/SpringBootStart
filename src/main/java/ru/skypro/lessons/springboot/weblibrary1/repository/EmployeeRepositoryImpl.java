package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final List<Employee> employeeList = List.of(
            new Employee("Катя", 90_000),
            new Employee("Дима", 110_000),
            new Employee("Олег", 150_000),
            new Employee("Вика", 150_000));


    @Override
    public List<Employee> allEmployees() {
        return employeeList;
    }

    }


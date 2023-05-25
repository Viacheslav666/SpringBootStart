package ru.skypro.lessons.springboot.weblibrary1.repository;

import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {
    public List<Employee> allEmployees();

}

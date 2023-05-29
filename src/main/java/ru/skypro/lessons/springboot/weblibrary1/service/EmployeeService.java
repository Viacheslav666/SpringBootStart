package ru.skypro.lessons.springboot.weblibrary1.service;

import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();

    public List<Employee> showEmployee();
    public int sumSalary();
    public int minSalary();
    public int maxSalary();
    public List<Employee> highSalary();
}

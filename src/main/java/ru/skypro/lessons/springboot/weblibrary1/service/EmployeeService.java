package ru.skypro.lessons.springboot.weblibrary1.service;

import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();

    public List<Employee> showEmployee();

    public Employee createEmployee(int id, String name, int salary);

    public void editEmployee(int id);
    public List<Employee> getEmployee(int id);
    public void deliteEmployee(int id);
    public List<Employee> getEmployeeSalaryHigher(int salary);
    public int sumSalary();
    public int minSalary();
    public int maxSalary();
    public List<Employee> highSalary();
}

package ru.skypro.lessons.springboot.weblibrary1.service;

import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.FullInfo;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public void addEmployee(Employee employee);
    public  List<EmployeeDTO> getAllEmployees();

    public List<EmployeeDTO> withHighestSalary();
    public List<EmployeeDTO> employeesPosition(Optional position);
    public List<FullInfo> fullInfo(int id);
    public List<EmployeeDTO> getEmployeeWithPaging(int pageIndex);

//    List<Employee> getAllEmployee();

//    public List<Employee> showEmployee();
//
//    public Employee createEmployee(int id, String name, int salary, Position position);
//
//    public void editEmployee(int id);
//    public List<Employee> getEmployee(int id);
//    public void deliteEmployee(int id);
//    public List<Employee> getEmployeeSalaryHigher(int salary);
//    public int sumSalary();
//    public int minSalary();
//    public int maxSalary();
//    public List<Employee> highSalary();
}

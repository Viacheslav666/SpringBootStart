package ru.skypro.lessons.springboot.weblibrary1.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeReport;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public void addEmployee(Employee employee);
    public  List<EmployeeFullInfo> getAllEmployees();

    public EmployeeDTO  withHighestSalary();
    public List<EmployeeFullInfo> employeesPosition(Integer position);
    public EmployeeFullInfo fullInfo(int id);
    public List<EmployeeDTO> getEmployeeWithPaging(int pageIndex);
    public EmployeeDTO upload(MultipartFile file) throws IOException;
    public List<EmployeeReport> getReport();
    public EmployeeDTO getEmployeeById(Integer id);


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

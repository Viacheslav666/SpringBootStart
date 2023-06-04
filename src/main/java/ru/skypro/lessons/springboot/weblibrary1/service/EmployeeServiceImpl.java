package ru.skypro.lessons.springboot.weblibrary1.service;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;
@EqualsAndHashCode
@ToString
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
    public Employee createEmployee(int id, String name, int salary) {
         Employee employee =  new Employee(id, name, salary);
         employeeRepository.allEmployees().add(employee);
        out.println(employee + "Cоздана и добавлена в список");
        return employee;
    }

    @Override
    public void editEmployee(int id ) {
         List<Employee> employees = new ArrayList<>();
        for (Employee employee1 : employeeRepository.allEmployees()) {
             if(id == employee1.getId()){
            employees.add(new Employee(1, "name", 250000));
        }}
    }

    @Override
    public List<Employee> getEmployee(int id) {
        return  employeeRepository.allEmployees().stream()
                .filter(employee -> employee.getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public void deliteEmployee(int id) {

        for (Employee employee1 : employeeRepository.allEmployees()) {
            if (id == employee1.getId()) {
                List<Employee> employees = new ArrayList<>();
                employees.remove(employee1);
            }
            }

    }

    @Override
    public List<Employee> getEmployeeSalaryHigher(int salary) {

    return      employeeRepository.allEmployees().stream()
                .filter(employee -> employee.getSalary() < salary)
                .collect(Collectors.toList());

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

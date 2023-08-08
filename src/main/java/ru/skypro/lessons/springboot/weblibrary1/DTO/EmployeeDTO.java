package ru.skypro.lessons.springboot.weblibrary1.DTO;

import lombok.*;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;


import java.io.Serializable;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeDTO implements Serializable {
    private Integer id;
    private String name;
    private int salary;
    private Integer department;
    private Position position;


    public static EmployeeDTO fromEmployee(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setPosition(employee.getPosition());
        employeeDTO.setDepartment(employee.getDepartment());
        return employeeDTO;
    }

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setId(this.getId());
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        employee.setPosition(this.getPosition());
        employee.setDepartment(this.getDepartment());
        return employee;
    }
}
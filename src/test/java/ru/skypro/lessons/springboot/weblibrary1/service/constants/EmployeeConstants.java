package ru.skypro.lessons.springboot.weblibrary1.service.constants;

import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConstants {
    public static final Employee EMPLOYEE = Employee.builder().build();
    public static final Position pos = new Position(1, "Java");
    public static final Report rep = new Report();
    public static final Employee EMPLOYEE1 = new Employee(1, "Kir", 20000, pos, rep);
    public static final Employee EMPLOYEE2 = new Employee(1, "Arch", 20000, pos, rep);
    public static final Employee EMPLOYEE3 = new Employee(1, "Kir", 20000, pos, rep);

    public static final List<Employee> EMPLOYEE_LIST = new ArrayList<>(List.of(EMPLOYEE1,
            EMPLOYEE2,
            EMPLOYEE3));
}

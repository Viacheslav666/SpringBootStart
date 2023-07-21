package ru.skypro.lessons.springboot.weblibrary1.service.service;

import jdk.dynalink.linker.LinkerServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee.EmployeeBuilder;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.skypro.lessons.springboot.weblibrary1.service.constants.EmployeeConstants.EMPLOYEE_LIST;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    public static final Position pos = new Position(1, "Java");
    public static final Report rep = new Report();
    private static  EmployeeDTO employeeDto;
@Mock
    private  EmployeeRepository employeeRepositoryMock;
@InjectMocks
    private EmployeeServiceImpl employeeServiceTest;

    @Test
    void getAllEmployees_Ok() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Kir", 200000, pos, rep));
        employees.add(new Employee(2, "Slava", 30000, pos, rep));
        when(employeeRepositoryMock.findAllEmployees()).thenReturn(employees);
        List<EmployeeDTO> employeeDTOs = employeeServiceTest.getAllEmployees();
        assertEquals(employees.size(), employeeDTOs.size());
    }

}
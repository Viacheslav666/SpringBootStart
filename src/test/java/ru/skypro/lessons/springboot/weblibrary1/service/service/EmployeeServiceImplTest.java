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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;
import static ru.skypro.lessons.springboot.weblibrary1.service.constants.EmployeeConstants.EMPLOYEE_LIST;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    public static final Position pos = new Position(1, "Java");
    public static final Position pos1 = new Position(1, "Java");
    public static final Report rep = new Report();
    private static EmployeeDTO employeeDto;
    @Mock
    private EmployeeRepository employeeRepositoryMock;
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

    @Test
    void withHighestSalary_Ok() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Kir", 200000, pos, rep));
        employees.add(new Employee(2, "Slava", 30000, pos, rep));
        when(employeeRepositoryMock.withHighestSalary()).thenReturn(employees);
        List<EmployeeDTO> highestPaidEmployees = employees.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
        List<EmployeeDTO> employeeDTOs = employeeServiceTest.withHighestSalary();
        verify(employeeRepositoryMock).withHighestSalary();
        assertEquals(highestPaidEmployees, employeeDTOs);

    }

    @Test
    void employeesPosition_Ok() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Kir", 200000, pos, rep));
        employees.add(new Employee(2, "Slava", 30000, pos1, rep));
        when(employeeRepositoryMock.getEmployeesByName(Optional.of(pos))).thenReturn(employees);
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee(1, "Kir", 200000, pos, rep));
        List<EmployeeDTO>employeeDTOList = expected.stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());

        List<EmployeeDTO> result = employeeServiceTest.employeesPosition(Optional.of(pos));
        assertEquals(employeeDTOList.get(0).getName(), result.get(0).getName());
    }

}e
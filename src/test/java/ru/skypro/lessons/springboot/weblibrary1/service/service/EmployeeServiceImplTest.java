package ru.skypro.lessons.springboot.weblibrary1.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary1.controller.EmployeeController;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.PagingAndSortingRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    public static final Position pos = new Position(1, "Java");
    public static final Position pos1 = new Position(1, "Java");
    private static final Employee employee = new Employee(1, "Kirill", 20000, 1, pos);
    private static final EmployeeDTO employeeDTO = EmployeeDTO.fromEmployee(employee);
    public static final Report rep = new Report();

    private static EmployeeFullInfo employeeFullInfo;
    private static PagingAndSortingRepository pagingAndSortingRepository;
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private EmployeeRepository employeeRepositoryMock;
    private static EmployeeController employeeController;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceTest;

    @Test
    void getAllEmployees_Ok() {
        List<EmployeeFullInfo> employees = new ArrayList<>();
        employees.add(new EmployeeFullInfo("Kir", 200000,"Java"));
        employees.add(new EmployeeFullInfo("Slava", 30000, "Pithon"));
        when(employeeRepositoryMock.findAllEmployees()).thenReturn(employees);
        List<EmployeeFullInfo> employeeDTOs = employeeServiceTest.getAllEmployees();
        assertEquals(employees.size(), employeeDTOs.size());
    }

    @Test

    void theHighestSalary_Ok() {
        when(employeeRepositoryMock.findFirstByOrderBySalaryDesc())
                .thenReturn(Optional.of(employee));
        assertEquals(employeeDTO, employeeServiceTest.withHighestSalary());


    }

    @Test
    void employeesPosition_Ok() {
        List<EmployeeFullInfo> employees = new ArrayList<>();
        employees.add(new EmployeeFullInfo("Kir", 200000, "Java"));
        employees.add(new EmployeeFullInfo("Slava", 30000, "Pithon"));
        when(employeeRepositoryMock.findEmployeeByPosition(1))
                .thenReturn(employees);
        assertIterableEquals(employees, employeeServiceTest.employeesPosition(1));
        verify(employeeRepositoryMock, times(1)).findEmployeeByPosition(1);
    }

    @Test
    void fullInfo_Ok() throws InterruptedException {
        int id = 1;
        List<EmployeeFullInfo> employees = new  ArrayList<>();
        employees.add(new EmployeeFullInfo("Kir", 200000, "Java"));
        when(employeeRepositoryMock.findById(id)).thenReturn(Optional.of(employee));
        assertEquals(employees, employeeServiceTest.fullInfo(1));
    }

    @Test
    void getEmployeeWithPaging_OK() {
        List<Employee> employeeList = List.of(
                new Employee(1, "Daria", 100,1, new Position(1, "Java")),
                new Employee(2, "Ilia", 1300, 2, new Position(2, "Pithon")));
        List<EmployeeDTO> employeeListDTO = List.of(
                new EmployeeDTO(1, "Daria", 100,1, new Position(1, "Java")),
                new EmployeeDTO(2, "Ilia", 1300, 2, new Position(2, "Pithon")));
                List<EmployeeFullInfo> employees = new ArrayList<>();
        employees.add(new EmployeeFullInfo("Kir", 200000, "Java"));
        employees.add(new EmployeeFullInfo( "Slava", 30000, "Pithon"));
        Page<Employee> employeePage = new PageImpl<>(employeeList);
        when(employeeRepositoryMock.findAll(PageRequest.of(0, 10)))
                .thenReturn(employeePage);
        assertIterableEquals(employeeListDTO, employeeServiceTest.getEmployeeWithPaging(0));
        verify(pagingAndSortingRepository, times(1)).findAll(PageRequest.of(0,10));
    }

    @Test
    void upload_NO_OK_Exception() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "multipartFile",
                "filename.txt",
                "application/json",
                "{\"id\" \":111\",\"name\": \"Kir\", \"salary\": 20000\",\"position:\"JS\"}".getBytes());

        assertThrows(IOException.class, () -> employeeServiceTest.upload(mockMultipartFile));
    }


}
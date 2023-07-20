package ru.skypro.lessons.springboot.weblibrary1.service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
@Mock
    private final EmployeeRepository employeeRepositoryMock;

    EmployeeServiceImplTest(EmployeeRepository employeeRepositoryMock) {
        this.employeeRepositoryMock = employeeRepositoryMock;
    }


    @Test
    void addEmployee() {

    }

    @Test
    void getAllEmployees() {
    }

    @Test
    void withHighestSalary() {
    }

    @Test
    void employeesPosition() {
    }

    @Test
    void fullInfo() {
    }

    @Test
    void getEmployeeWithPaging() {
    }

    @Test
    void upload() {
    }

    @Test
    void getEmployeeRepository() {
    }

    @Test
    void getObjectMapper() {
    }

    @Test
    void getLogger() {
    }

    @Test
    void getTest() {
    }

    @Test
    void setEmployeeRepository() {
    }

    @Test
    void setObjectMapper() {
    }

    @Test
    void setLogger() {
    }

    @Test
    void setTest() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}
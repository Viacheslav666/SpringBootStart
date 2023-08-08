package ru.skypro.lessons.springboot.weblibrary1.service.IntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeIntegationTest {
    @Autowired
    MockMvc mockMvc;

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeIntegationTest(EmployeeRepository repository) {
        this.repository = repository;
    }


    @BeforeEach
    void employeeList() {
        Report report = new Report(1, "file1");
        Report report1 = new Report(2, "file2");
        Position position = new Position(1, "Java");
        Position position2 = new Position(2, "Piton");

        List<Employee> employeeList = List.of(
                new Employee(1, "Kirill", 20000, 1, position),
                new Employee(2, "Slava", 200050, 2, position2),
                new Employee(3, "Daria", 200100, 3, position2)
        );
        repository.saveAll(employeeList);
    }


    @Test
    void getEmployeesWithSalaryHigherThan() throws Exception {
        employeeList();
        mockMvc.perform(get("/employees/withHighestSalary?salary=200100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Daria"));
    }

    @Test
    void  getTest() throws Exception {
        mockMvc.perform(get("/employees/test"))
                .andExpect(status().isOk());
    }

    @Test
    void employeesPositionTest() throws Exception {
        mockMvc.perform(get("/employees")
                        .param("position", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Kirill"));
    }

    @Test
    void getEmployeeByIdFullInfo() throws Exception {

        mockMvc.perform(get("/employees/1/fullInfo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kirill"));
    }

    @Test
    void addEmployeeFromFile() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employeeDTO);
        MockMultipartFile file = new MockMultipartFile("file", "employee.json", MediaType.MULTIPART_FORM_DATA_VALUE, json.getBytes());

        mockMvc.perform(multipart("/employees/upload")
                        .file(file))
                .andExpect(status().isOk());
        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kirill"));
    }


}

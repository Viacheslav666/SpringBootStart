package ru.skypro.lessons.springboot.weblibrary1.service.IntegrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.el.stream.Stream;
import org.json.JSONObject;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.controller.EmployeeController;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.UserRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeFileService;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeIntegationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    EmployeeRepository employeeRepository;


    @BeforeEach
    void employeeList() {
        Position position = new Position(1, "Java");
        Position position2 = new Position(2, "Piton");
        List<Employee> employeeList = List.of(
                new Employee(1, "Kirill", 20000, position2, new Report(1, "file")),
                new Employee(2, "Slava", 200050, position, new Report(2, "file2")),
                new Employee(3, "Daria", 200100, position2, new Report(3, "file3"))
        );
        employeeRepository.saveAll(employeeList);
    }



    @Test
    void getEmployeesWithSalaryHigherThan () throws Exception {
        employeeList();
        mockMvc.perform(get("/employees/withHighestSalary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Daria"));
    }

    @Test
    void employeesPositionTest() throws Exception {
        employeeList();
        mockMvc.perform(get("/employees")
                        .param("position", "Java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Kirill"));
    }

    @Test
    void getEmployeeByIdFullInfo() throws Exception {
        employeeList();
        Integer id = 1;
        mockMvc.perform(get("/employee/{id}/fullInfo", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kirill"));
    }

    @Test
    void addEmployeeFromFile() throws Exception {
        employeeList();
        EmployeeDTO employeeDTO = new EmployeeDTO(4, "Daria", 200100, new Position(4,"Sql"), new Report(3, "file3"));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employeeDTO);
        MockMultipartFile file = new MockMultipartFile("file", "employee.json", MediaType.MULTIPART_FORM_DATA_VALUE, json.getBytes());

        mockMvc.perform(multipart("/employees/upload")
                        .file(file))
                .andExpect(status().isOk());
        mockMvc.perform(get("/employee/{id}", 4))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Petr"));
    }


}

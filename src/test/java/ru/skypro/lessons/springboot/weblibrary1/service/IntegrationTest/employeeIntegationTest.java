package ru.skypro.lessons.springboot.weblibrary1.service.IntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.el.stream.Stream;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.UserRepository;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class employeeIntegationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;
    void EmployeeList() {
        Position position = new Position(1, "Java");
        Position position2 = new Position(2, "Piton");
        List<Employee> employeeList = List.of(
                new Employee(1, "Kirill", 20000, position2, new Report(1,"file")),
                new Employee(1, "Slava", 200050, position, new Report(2,"file2")),
                new Employee(1, "Daria", 200100, position2, new Report(3, "file3"))
        );
        employeeRepository.saveAll(employeeList);
    }

    @Test
    void getEmployeesWithSalaryHigherThan() throws Exception {
        EmployeeList();
        mockMvc.perform(get("/withHighestSalary")
                        .content("200100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Daria"));
    }

    @Test
    void employeesPositionTest() throws Exception {
        mockMvc.perform(get("/employees")
                        .content("Java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Kirill"));
    }

}

package ru.skypro.lessons.springboot.weblibrary1.service.IntegrationTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReportTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportService reportService;

    @BeforeEach
    void employeeList() {
        Report report = new Report(2, "file2");
        Report report1 =  new Report(2, "file2");
        Position position = new Position(1, "Java");
        Position position2 = new Position(2, "Piton");
        List<Employee> employeeList = List.of(
                new Employee(1, "Kirill", 20000, position2, report),
                new Employee(2, "Slava", 200050, position, report),
                new Employee(3, "Daria", 200100, position2, report1)
        );
        employeeRepository.saveAll(employeeList);
    }
    @Test
    void getReportById() throws Exception {
        employeeList();
        Integer id = reportService.createReport();
        mockMvc.perform(get("/report/{id}", id))
                .andExpect(status().isOk());
    }
}

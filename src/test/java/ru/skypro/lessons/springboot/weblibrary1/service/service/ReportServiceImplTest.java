package ru.skypro.lessons.springboot.weblibrary1.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeReport;
import ru.skypro.lessons.springboot.weblibrary1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.controller.EmployeeController;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;

import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportWithPathRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeServiceImpl;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportServiceImpl;
import ru.skypro.lessons.springboot.weblibrary1.pojo.ReportWithPath;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {
    @Mock
    private ReportWithPathRepository reportWithPathRepository;
    @Mock
    private ReportRepository reportRepositoryMock;
    @InjectMocks
    private ReportServiceImpl reportServiceTest;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceTest;
    private final Position pos = new Position(1, "Java");
    private final Position pos1 = new Position(1, "Java");
    private final Report rep = new Report();
    private final ObjectMapper objectMapper = new ObjectMapper();



    @Test
    void CreateReport_OK() throws IOException {
        List<EmployeeReport> employeeReportList = List.of(
                new EmployeeReport(1,2L,10,10,10),
                new EmployeeReport(2, 1L, 20,20, 20));
        when(employeeServiceTest.getReport())
                .thenReturn(employeeReportList);
        String json = objectMapper.writeValueAsString(employeeServiceTest.getReport());
        Report report =  new Report();
        report.setFile(json);
        Report reportExpected = new Report();
        reportExpected.setFile(json);
        reportExpected.setId(1);
        when(reportRepositoryMock.save(report)).thenReturn(reportExpected);
        assertEquals(1, reportServiceTest.createReport());
    }

    @Test
   public void upload_Ok() throws IOException {
       List<EmployeeReport> employeeReportList = List.of(
               new EmployeeReport(1,2L,10,10,10),
               new EmployeeReport(2, 1L, 20,20, 20));
       when(employeeServiceTest.getReport())
               .thenReturn(employeeReportList);
       String json = objectMapper.writeValueAsString(employeeServiceTest.getReport());
       ReportWithPath report =  new ReportWithPath();
       File file = new File("report.json");
       Files.writeString(file.toPath(), json);
       report.setPath(file.getAbsolutePath());
       ReportWithPath reportExpected = new ReportWithPath();
       reportExpected.setPath(file.getAbsolutePath());
       reportExpected.setId(1);
       when(reportWithPathRepository.save(report)).thenReturn(reportExpected);
       assertEquals(1, reportServiceTest.upload(1));

   }


}
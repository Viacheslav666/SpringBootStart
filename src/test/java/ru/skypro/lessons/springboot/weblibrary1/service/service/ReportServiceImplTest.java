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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary1.DTO.EmployeeReport;
import ru.skypro.lessons.springboot.weblibrary1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.controller.EmployeeController;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeServiceImpl;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {
    @Mock
    private ReportRepository reportRepositoryMock;
    @Mock
    private ReportServiceImplTest reportMock;
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
        void Upload_Ok() throws JsonProcessingException {
       String json = objectMapper.writeValueAsString(employeeServiceTest.getReport());
       Report report = new Report();
       report.setId(1);
       report.setFile(json);
       when(reportRepositoryMock.findById(1))
               .thenReturn(Optional.of(report));
       ResponseEntity<Resource> expected = ResponseEntity.ok().
               header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "report.json" + "\"")
               .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
               .body(new ByteArrayResource(json.getBytes()));
       assertEquals(expected, reportServiceTest.getReportById(1));
        }



}
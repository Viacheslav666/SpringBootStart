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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertThrows;
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

    private final Report REPORT_1 = new Report( 1, "путь_1");
    private final Report REPORT_2 = new Report(2,  "путь_2");
    private final Report REPORT_3 = new Report(3,  "путь_3");
    private final List<Report> REPORT_LIST = new ArrayList<>(List.of(REPORT_1, REPORT_2, REPORT_3));



    @Test
    public void ThrowExceptionWhenFindReportById_Ok() {
        when(reportRepositoryMock.findById(1))
                .thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> reportServiceTest.getReportById(1));
    }


}
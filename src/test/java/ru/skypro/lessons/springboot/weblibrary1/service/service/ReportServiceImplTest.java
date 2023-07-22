package ru.skypro.lessons.springboot.weblibrary1.service.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.weblibrary1.controller.EmployeeController;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;
import ru.skypro.lessons.springboot.weblibrary1.service.EmployeeServiceImpl;
import ru.skypro.lessons.springboot.weblibrary1.service.ReportServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReportServiceImplTest {
    @Mock
    private ReportRepository reportRepositoryMock;
    @InjectMocks
    private ReportServiceImpl reportServiceTest;


}

package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.repository.EmployeeRepository;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class EmployeeFileService {
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;
    Logger logger = LoggerFactory.getLogger(EmployeeFileService.class);


    public void processJsonFile(MultipartFile multipartFile) {
        logger.info("Parsing json file");
        Employee employee = new Employee();
        try (JsonParser jsonParser = objectMapper.getFactory().createParser(multipartFile.getInputStream())) {
            while (jsonParser.nextToken() != null) {
                if (jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                    employee = objectMapper.readValue(jsonParser, Employee.class);
                    employeeRepository.save(employee);
                }
            }
            logger.debug("The file was saved successfully" + employee);
            logger.error("File not found = " + employee);
        } catch (IOException e) {
            System.out.println("File not found " + e);
            logger.error("File not found = " + employee, e);
        }
    }
}
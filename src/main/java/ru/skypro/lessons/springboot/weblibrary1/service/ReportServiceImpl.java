package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.skypro.lessons.springboot.weblibrary1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class  ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final EmployeeService employeeService;
    ObjectMapper objectMapper = new ObjectMapper();
Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);


    public ReportServiceImpl(ReportRepository reportRepository, EmployeeService employeeService) {
        this.reportRepository = reportRepository;
        this.employeeService = employeeService;
    }

    @Override
    public int createReport() throws IOException {
        logger.info("Was invoked method to add report to DB");
        Integer i = 0;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(employeeService.getReport());
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setFile(json);
            i = reportRepository.save(reportDTO.toReport()).getId();
            logger.debug("Report was adding to DB with id={}", i);
        } catch (JsonProcessingException e) {
            logger.error("Was error to save object report as json string type", e);
        }
        return i;


    }

    @Override
    public ResponseEntity<Resource> upload(int id) {
        logger.info("Was invoked method for getting report to DB with id={}", id);
        ReportDTO reportDTO = ReportDTO.fromReport(reportRepository.findById(id).orElseThrow(() -> {
            IllegalArgumentException e = new IllegalArgumentException("Введен не корректный ID");
            return e;
        }));
        String fileName = "report.json";
        String json = reportDTO.getFile();
        Resource resource = new ByteArrayResource(json.getBytes());
        logger.debug("Received the report {}", reportDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }


    @Override
    public String saveReportToFile(String reportJson) {
        logger.info("Was invoked method for saveReportToFile{}",reportJson);
        String fileName = "report-" + System.currentTimeMillis() + ".json";
        try {
            Path filePath = Paths.get(fileName);
            Files.write(filePath, reportJson.getBytes());
            return filePath.toString();
        } catch (IOException e) {
            logger.error("Failed to save report to file",reportJson,e);
            throw new RuntimeException("Failed to save report to file", e);
        }
    }
    @Override
    public ResponseEntity<Resource> getReportById(Integer id) {
        logger.info("Was invoked method for getting report to DB with id={}", id);
        ReportDTO reportDTO = ReportDTO.fromReport(reportRepository.findById(id).orElseThrow(() -> {
            IllegalArgumentException e = new IllegalArgumentException("Введен не корректный ID");
            logger.error("Received invalid id={}",id, e);
            return e;
        }));
        String fileName = "report.json";
        String json = reportDTO.getFile();
        Resource resource = new ByteArrayResource(json.getBytes());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(resource);
    }

}

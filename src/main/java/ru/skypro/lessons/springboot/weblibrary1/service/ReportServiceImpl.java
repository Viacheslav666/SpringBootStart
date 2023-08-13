package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.skypro.lessons.springboot.weblibrary1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Service
public class  ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final EmployeeService employeeService;
    ObjectMapper objectMapper = new ObjectMapper();
Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);



    @Override
    public Integer createReport() {
        List<Report> reports = reportRepository.createReport();
        try {
            String text = objectMapper.writeValueAsString(reports);
            Report report = new Report();
            reportRepository.save(report);
            String fileName =
                    "c:\\Users\\Ruslan\\" +
                            "report" + report.getId() + ".json";
            File file = new File(fileName);
            file.createNewFile();
            report.setFile(fileName);
            writeTextToFile(text, fileName);
            reportRepository.save(report);
            logger.info("Отчёт сохранён, reportId = " + report.getId());
            return report.getId();
        }  catch (IOException e) {
            logger.error("Отчёт не сформирован");
            e.printStackTrace();
        } return 0;
    }
    private static void writeTextToFile(String text, String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.write(path, text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
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

package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
@Service
public class  ReportServiceImpl implements ReportService {
    public ReportRepository reportRepository;
    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public int createReport() {
       List<ReportDTO> reportDTOS = reportRepository.createReport();
        try {
            String json = objectMapper.writeValueAsString(reportDTOS);
            String pathJson = saveReportToFile(json);
            Report report = new Report();
            report.setFilePath(pathJson);
            reportRepository.save(report);
         return report.getId();
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
        } return 0;

    }

    @Override
    public ResponseEntity<Report> upload(int id) {
        ResponseEntity<Report> report = reportRepository.readReportById(id);
        return report;
    }

    @Override
    public String saveReportToFile(String reportJson) {
        String fileName = "report-" + System.currentTimeMillis() + ".json";
        try {
            Path filePath = Paths.get(fileName);
            Files.write(filePath, reportJson.getBytes());
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save report to file", e);
        }
    }

}

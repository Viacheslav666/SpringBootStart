package ru.skypro.lessons.springboot.weblibrary1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary1.DTO.ReportDTO;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
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
@NoArgsConstructor
@ToString
@Data
@Service
@Profile("!test")
public class  ReportServiceImpl implements ReportService {
    @Autowired
    public ReportRepository reportRepository;
    ObjectMapper objectMapper = new ObjectMapper();
Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    @Value("${app.env}")
    private String dev;
    @Override
    public int createReport() throws IOException {
        logger.info("Was invoked method for createReport");
       List<ReportDTO> reportDTOS = reportRepository.createReport();
            String json = objectMapper.writeValueAsString(reportDTOS);
            String pathJson = saveReportToFile(json);
            Report report = new Report();
            report.setFilePath(pathJson);
            reportRepository.save(report);
         return report.getId();


    }

    @Override
    public ResponseEntity<Report> upload(int id) {
        logger.info("Was invoked method for upload{}",id);
        ResponseEntity<Report> report = reportRepository.readReportById(id);
        return report;
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

}

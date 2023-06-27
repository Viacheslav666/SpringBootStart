package ru.skypro.lessons.springboot.weblibrary1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;
import ru.skypro.lessons.springboot.weblibrary1.repository.ReportRepository;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class ReportDTO {
    public ReportRepository reportRepository;

    private Integer id;
    private Position name;
    private String name1;
    private int countEmployee;
    private int maxSalary;
    private int minSalary;
    private int avgSalary;
    private EmployeeDTO employeeDTO;

    public ReportDTO(Report report) {
    }

    public static ReportDTO fromReport(Report report) {
        ReportDTO reportDTO = new ReportDTO(report);
reportDTO.setId(report.getId());
reportDTO.setName1(report.getFilePath());
reportDTO.getCountEmployee();
reportDTO.getMaxSalary();
reportDTO.getMinSalary();
reportDTO.getAvgSalary();
return reportDTO;
    }

}

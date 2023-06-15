package ru.skypro.lessons.springboot.weblibrary1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Position;
import ru.skypro.lessons.springboot.weblibrary1.pojo.Report;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class ReportDTO {
    private Integer id;
    private Position name;
    private Report name1;
    private int countEmployee;
    private int maxSalary;
    private int minSalary;
    private int avgSalary;

    public ReportDTO(Report report) {
    }

    public static ReportDTO fromReport(Report report) {
        ReportDTO reportDTO = new ReportDTO(report);
reportDTO.setId(report.getId());
reportDTO.setName1(report.getName());
return reportDTO;
    }

    private void setName1(String name) {
    }
}

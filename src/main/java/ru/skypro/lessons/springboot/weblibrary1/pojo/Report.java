package ru.skypro.lessons.springboot.weblibrary1.pojo;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Lob
    @Column(name = "file", columnDefinition="text")
    private String file;


    public Report(String positionName, Long count, Integer max, Integer min, Double avg) {
        this.file = "Position: " + positionName + ", countOfEmployee: " + count + ", maxSalary: " + max + ", minSalary: " + min + ", avgSalary: " + avg;
    }

}

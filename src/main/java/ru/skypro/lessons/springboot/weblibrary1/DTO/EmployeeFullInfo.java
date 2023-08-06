package ru.skypro.lessons.springboot.weblibrary1.DTO;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeFullInfo {
    private String name;
    private int salary;
    private String positionName;
    }
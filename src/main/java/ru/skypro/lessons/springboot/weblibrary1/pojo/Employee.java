package ru.skypro.lessons.springboot.weblibrary1.pojo;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private int id;
    private String name;
    private int salary;

}

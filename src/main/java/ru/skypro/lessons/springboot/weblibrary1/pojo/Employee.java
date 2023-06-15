package ru.skypro.lessons.springboot.weblibrary1.pojo;

import jakarta.persistence.*;
import lombok.*;
@Builder(toBuilder = true)

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    private String name;
    @Lob
    private int salary;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_position")
    private Position position;


}

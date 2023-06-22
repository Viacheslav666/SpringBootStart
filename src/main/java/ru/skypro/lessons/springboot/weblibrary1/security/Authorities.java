package ru.skypro.lessons.springboot.weblibrary1.security;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "role")
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
    @Column
    private String role;
}

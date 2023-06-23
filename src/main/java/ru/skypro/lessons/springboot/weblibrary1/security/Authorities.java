package ru.skypro.lessons.springboot.weblibrary1.security;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roleUser")
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

    @Column(nullable = false, unique = true)
    private String role;
}

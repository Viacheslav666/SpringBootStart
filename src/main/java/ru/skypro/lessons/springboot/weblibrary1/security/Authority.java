package ru.skypro.lessons.springboot.weblibrary1.security;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "role1")

public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_role;

    @Column(nullable = false, unique = true)
    private String role;
}
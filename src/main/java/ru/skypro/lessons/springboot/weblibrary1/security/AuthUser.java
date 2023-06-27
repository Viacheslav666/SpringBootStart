package ru.skypro.lessons.springboot.weblibrary1.security;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Entity
@Table(name = "auth_user")
public class AuthUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    private boolean enabled;

    @JoinColumn(name = "role_id")
    @OneToMany(fetch = FetchType.EAGER)
    private List <Authority> authorityList;
}

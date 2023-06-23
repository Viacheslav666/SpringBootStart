package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.lessons.springboot.weblibrary1.security.AuthUser;

public interface UserRepository extends JpaRepository<AuthUser, Integer> {

    AuthUser findByUsername(String username);
}

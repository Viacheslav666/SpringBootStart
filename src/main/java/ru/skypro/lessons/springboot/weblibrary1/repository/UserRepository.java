package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary1.security.AuthUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AuthUser, Integer> {

    Optional <AuthUser> findByUsername(String username);
}

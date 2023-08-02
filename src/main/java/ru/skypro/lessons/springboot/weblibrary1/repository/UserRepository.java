package ru.skypro.lessons.springboot.weblibrary1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary1.security.AuthUser;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<AuthUser, Integer> {

  AuthUser findByUsername(String username);
}

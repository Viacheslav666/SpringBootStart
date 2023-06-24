package ru.skypro.lessons.springboot.weblibrary1.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Component
public class AuthUserDto {
    private long id;
    private String name;
    private String password;
    public Role role;


}

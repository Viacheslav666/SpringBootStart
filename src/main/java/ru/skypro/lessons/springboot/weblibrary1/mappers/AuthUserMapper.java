package ru.skypro.lessons.springboot.weblibrary1.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import ru.skypro.lessons.springboot.weblibrary1.security.AuthUser;
import ru.skypro.lessons.springboot.weblibrary1.security.AuthUserDto;
@Data
@AllArgsConstructor
@Component
public class AuthUserMapper {
    public AuthUserDto toDo(AuthUser user) {
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setId(user.getId());
        authUserDto.setName(user.getUsername());
        authUserDto.setPassword(user.getPassword());
        authUserDto.setRole(user.getRole());
        return authUserDto;

    }
}

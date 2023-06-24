package ru.skypro.lessons.springboot.weblibrary1.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary1.mappers.AuthUserMapper;
import ru.skypro.lessons.springboot.weblibrary1.repository.UserRepository;

@Service
@Data
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private  SecurityUserPrincipal securityUserPrincipal;
    private final AuthUserDto authUserDto;
    private AuthUserMapper mapper;



    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
     var user =  userRepository.findByUsername(name)

                .orElseThrow(()->new IllegalArgumentException("Пользовательр не найден"));
     securityUserPrincipal.setUserDto(mapper.toDo(user));
         return  securityUserPrincipal;
    }
}

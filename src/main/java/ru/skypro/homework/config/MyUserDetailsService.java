package ru.skypro.homework.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UsersService;

import javax.transaction.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UsersService usersService;

    public MyUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersService.findByEmail(email);
        return new UserSecurityDTO(user);
    }
}

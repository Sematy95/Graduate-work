package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.dto.user.LoginDto;
import ru.skypro.homework.dto.user.RegisterDto;
import ru.skypro.homework.exception.InvalidCredentialsException;
import ru.skypro.homework.exception.UserAlreadyExistsException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.mappers.UserMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MyUserDetailsService myUserDetailsService;
    private final ImageServiceImpl imageService;


    @Override
    public boolean login(LoginDto loginDto) {
        log.info("Trying to login: {}", loginDto.getUsername());
        UserDetails user = myUserDetailsService.loadUserByUsername(loginDto.getUsername());
        if (!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }

        log.info("Успешный вход пользователя: {}", user.getUsername());
        return true;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        log.info("Registration attempt: {}", registerDto.getUsername());
        if (userRepository.existsByEmail(registerDto.getUsername())) {
            throw new UserAlreadyExistsException(registerDto.getUsername());
        }
        User registeredUser = userMapper.RegisterDtoToUser(registerDto);
        registeredUser.setPassword(encoder.encode(registerDto.getPassword()));
        registeredUser.setImage("no_image.png");
        userRepository.save(registeredUser);
        if (userRepository.existsByEmail(registeredUser.getEmail())) log.info("Registration succeed");
        return true;
    }

}

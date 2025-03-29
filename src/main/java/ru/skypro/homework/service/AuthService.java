package ru.skypro.homework.service;

import ru.skypro.homework.dto.user.LoginDto;
import ru.skypro.homework.dto.user.RegisterDto;

public interface AuthService {

    boolean register(RegisterDto registerDto);

    boolean login(LoginDto loginDto);
}

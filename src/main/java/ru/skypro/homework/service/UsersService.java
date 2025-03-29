package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.GetUserDto;
import ru.skypro.homework.dto.user.SetPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface UsersService {

    User findByEmail(String email);

    void setPassword(SetPasswordDto setPasswordDto);

    GetUserDto getAuthorizedUserInfo();

    UserDto updateUserInfo(UpdateUserDto updateUserDto);

    void updateUserImage(MultipartFile file);

    byte[] getUserImage(int id) throws IOException;
}

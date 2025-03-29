package ru.skypro.homework.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import ru.skypro.homework.dto.user.*;
import ru.skypro.homework.model.User;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserMapper {

    LoginDto userToLoginDto(User user);

    RegisterDto userToRegisterDto(User user);

    @Mapping(source = "username", target = "email")
    User RegisterDtoToUser(RegisterDto registerDto);

    @Mapping(source = "password", target = "currentPassword")
    SetPasswordDto userToSetPasswordDto(User user);

    void updateUserFromUpdateUserDto(UpdateUserDto updateUserDto, @MappingTarget User user);

    UpdateUserDto userToUpdateDto(User user);

    UserDto userToUserDto(User user);

    @Mapping(target = "image", expression = "java(path + user.getId())")
    GetUserDto userToGetUserDto(User user, String path);

    UpdateUserDto userToUpdateUserDto(User user);


}

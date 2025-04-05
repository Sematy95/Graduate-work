package ru.skypro.homework.service.mappers;

import org.junit.jupiter.api.Test;

import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.user.*;
import ru.skypro.homework.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    User oleg = new User(1,"123456","Oleg","Smagin","+79215600890","email@mail.ru", Role.USER,"112");


    @Test
    void userToUserDto() {

        UserDto olegUserDto = mapper.userToUserDto(oleg);
        System.out.println("olegUserDto = " + olegUserDto);
        assertEquals (olegUserDto.getId(), oleg.getId());
        assertEquals (olegUserDto.getEmail(), oleg.getEmail());
        assertEquals (olegUserDto.getFirstName(), oleg.getFirstName());
        assertEquals (olegUserDto.getLastName(), oleg.getLastName());
        assertEquals (olegUserDto.getPhone(), oleg.getPhone());
        assertEquals (olegUserDto.getImage(), oleg.getImage());


    }

    @Test
    void userToLoginDto() {

        LoginDto olegLoginDto = mapper.userToLoginDto(oleg);
        System.out.println("olegLoginDto = " + olegLoginDto);
        assertEquals(olegLoginDto.getUsername(), oleg.getEmail());
        assertEquals(olegLoginDto.getPassword(), oleg.getPassword());
    }

    @Test
    void userToRegisterDto() {

        RegisterDto olegRegisterDto = mapper.userToRegisterDto(oleg);
        System.out.println("olegRegisterDto = " + olegRegisterDto);
        assertEquals(olegRegisterDto.getUsername(), oleg.getEmail());
        assertEquals(olegRegisterDto.getPassword(), oleg.getPassword());
        assertEquals(olegRegisterDto.getFirstName(), oleg.getFirstName());
        assertEquals(olegRegisterDto.getLastName(), oleg.getLastName());
        assertEquals(olegRegisterDto.getPhone(), oleg.getPhone());
        assertEquals(olegRegisterDto.getRole(), oleg.getRole());


    }

    @Test
    void userToSetPasswordDto() {

        SetPasswordDto olegSetPasswordDto = mapper.userToSetPasswordDto(oleg);
        System.out.println("olegSetPasswordDto = " + olegSetPasswordDto);
        assertEquals(olegSetPasswordDto.getCurrentPassword(), oleg.getPassword());
    }


    @Test
    void userToGetUserDto() {

        System.out.println("mapper.userToGetUserDto(oleg) = " + mapper.userToGetUserDto(oleg));

    }
}

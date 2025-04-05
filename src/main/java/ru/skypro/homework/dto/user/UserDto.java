package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents an object with authorized user's info: id, email, firstname, lastName, phone, role and image.
 */
//to delete??
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    @Schema( description = "id пользователя")
    private Integer id;

    @Schema( description = "логин пользователя")
    private String email;

    @Schema( description = "имя пользователя")
    private String firstName;

    @Schema( description = "фамилия пользователя")
    private String lastName;

    @Schema( description = "телефон пользователя")
    private String phone;

    @Schema( description = "ссылка на аватар пользователя")
    private String image;
}



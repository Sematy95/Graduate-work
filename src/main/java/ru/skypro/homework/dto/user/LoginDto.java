package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents an object with data for authorization - username and password
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    @Schema(minLength = 4, maxLength = 32, description = "логин")
    private String username;

    @Schema(minLength = 8, maxLength = 16, description = "пароль")
    private String password;

}

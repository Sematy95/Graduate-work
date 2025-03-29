package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.validation.constraints.Pattern;

/**
 * Represents an object with data for registration - username, password, firstname, lastname, phone, role
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterDto {
    @Schema(minLength = 4, maxLength = 32, description = "логин")
    private String username;

    @Schema(minLength = 8, maxLength = 16, description = "пароль")
    private String password;

    @Schema(minLength = 2, maxLength = 16, description = "имя пользователя")
    private String firstName;

    @Schema(minLength = 2, maxLength = 16, description = "фамилия пользователя")
    private String lastName;

    @Schema(description = "телефон пользователя")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Используйте требуемый формат")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role;

}



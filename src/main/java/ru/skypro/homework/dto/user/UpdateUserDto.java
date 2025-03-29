package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Represents a data object for updating user's info
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserDto {
    @Schema(minLength = 3, maxLength = 10, description = "имя пользователя")
    private String firstName;

    @Schema(minLength = 3, maxLength = 10, description = "фамилия пользователя")
    private String lastName;

    @Schema(description = "телефон пользователя")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Используйте требуемый формат")
    private String phone;

}

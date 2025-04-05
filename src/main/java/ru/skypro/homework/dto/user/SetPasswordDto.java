package ru.skypro.homework.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents a data object for updating password
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetPasswordDto {
    @Schema(minLength = 8, maxLength = 16, description = "текущий пароль")
    private String currentPassword;

    @Schema(minLength = 8, maxLength = 16, description = "новый пароль")
    private String newPassword;

}

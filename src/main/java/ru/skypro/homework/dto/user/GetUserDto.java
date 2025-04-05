package ru.skypro.homework.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import ru.skypro.homework.dto.Role;

/**
 * Represents an object with authorized user's info: id, email, firstname, lastName, phone, role and image.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetUserDto {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

}


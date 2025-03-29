package ru.skypro.homework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "[EXCEPTION] User Not Found Exception")
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super("Such user wasn't found: " + username);
    }
}

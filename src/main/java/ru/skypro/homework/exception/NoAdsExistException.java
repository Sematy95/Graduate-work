package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "[EXCEPTION] No Ads Exist Exception")
public class NoAdsExistException extends RuntimeException {
    public NoAdsExistException() {
        super("no ads exist");
    }
}

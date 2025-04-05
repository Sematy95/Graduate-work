package ru.skypro.homework.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String userName) {
        super(userName);
    }
}

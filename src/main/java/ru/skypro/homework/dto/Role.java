package ru.skypro.homework.dto;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    public String getValue() {
        return value;
    }

    Role(String value) {
        this.value = value;
    }
}

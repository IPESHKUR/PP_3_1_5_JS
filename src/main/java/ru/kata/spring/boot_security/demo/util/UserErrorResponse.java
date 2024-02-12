package ru.kata.spring.boot_security.demo.util;

public class UserErrorResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserErrorResponse(String message) {
        this.message = message;
    }
}

package ru.kata.spring.boot_security.demo.util;

public class UserNotBeUpdatedException extends RuntimeException {
    public UserNotBeUpdatedException(String message) {
        super(message);
    }

}

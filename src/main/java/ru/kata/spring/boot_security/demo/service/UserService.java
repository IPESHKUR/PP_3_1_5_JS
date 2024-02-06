package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserById(Long id);

    void saveUser(UserDto userDto);

    void deleteUsers(Long id);

    void updateUser(UserDto user);

    boolean existsByUsername(String username);

    boolean uniqueUsername (String username);
}

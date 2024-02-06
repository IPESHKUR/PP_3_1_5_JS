package ru.kata.spring.boot_security.demo.Dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    User getUserById(Long id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUsers(Long id);

    void updateUser(User user);

    boolean existsByUsername(String username);

    User getUserByUsername(String username);

}
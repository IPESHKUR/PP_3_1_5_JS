package ru.kata.spring.boot_security.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {
    private Long id;
    @NotEmpty(message = "The name cannot be empty")
    @Size(min = 2, max = 40, message = "The name can contain from 2 to 40 characters")
    private String name;
    @NotEmpty(message = "The surname cannot be empty")
    @Size(min = 2, max = 40, message = "The surname can contain from 2 to 40 characters")
    private String surname;
    @NotNull
    @Min(value = 1, message = "Age must be greater than 0")
    private int age;
    @NotEmpty(message = "The username cannot be empty")
    @Size(min = 2, max = 40, message = "The username can contain from 2 to 40 characters")
    private String username;
    @NotEmpty(message = "The password cannot be empty")
    @Size(min = 2, max = 200, message = "The password can contain from 2 to 200 characters")
    private String password;
    private String[] roles;

    public UserDto() {
    }
    public UserDto(String name, String surname, int age, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public UserDto(String name, String surname, int age, String username, String password, String[] roles) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}

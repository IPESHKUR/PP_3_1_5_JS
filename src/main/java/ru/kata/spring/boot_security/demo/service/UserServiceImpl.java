package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Dao.RoleDao;
import ru.kata.spring.boot_security.demo.Dao.UserDao;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            user = userDao.getUserByUsername(username);
        } catch (NoSuchElementException e) {
            return new User();
        }
        return userDao.getUserByUsername(username);
    }

    public boolean uniqueUsername (String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        Set<Role> roles = roleDao.getRolesByNames(Arrays.asList(userDto.getRoles()));
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setSurname(userDto.getSurname());
        newUser.setAge(userDto.getAge());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRoles(roles);
        userDao.saveUser(newUser);
    }

    @Override
    @Transactional
    public void deleteUsers(Long id) {
        userDao.deleteUsers(id);
    }

    @Override
    @Transactional
    public void updateUser(UserDto userDto) {
        Set<Role> roles = roleDao.getRolesByNames(Arrays.asList(userDto.getRoles()));
        User newUser = new User();
        newUser.setId(userDto.getId());
        newUser.setName(userDto.getName());
        newUser.setSurname(userDto.getSurname());
        newUser.setAge(userDto.getAge());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRoles(roles);
        userDao.updateUser(newUser);
    }

}




package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class UsersController {

    private final UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userInfo(Model model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getName();
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        return "user";
    }

}

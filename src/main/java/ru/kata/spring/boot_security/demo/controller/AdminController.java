package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(ModelMap model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        List<User> listOfUsers = userService.getAllUsers();
        model.addAttribute("admin", listOfUsers);
        return "admin";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (userService.existsByUsername(userDto.getUsername())) {
            bindingResult.rejectValue("username",
                    "Username already exists", "Username already exists");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto",
                    bindingResult);
            redirectAttributes.addFlashAttribute("userDto", userDto);
        }
        userService.saveUser(userDto);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult,
                             @PathVariable ("id") Long id, RedirectAttributes redirectAttributes) {
        User userByUsername = userService.getUserByUsername(userDto.getUsername());
        if (userService.uniqueUsername(userDto.getUsername()) && !userByUsername.getId().equals(id)) {
            bindingResult.rejectValue("username",
                    "Username already exists", "Username already exists");
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto",
                    bindingResult);
            redirectAttributes.addFlashAttribute("userDto", userDto);
            return "redirect:/admin";
        }
        userService.updateUser(userDto);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUsers(id);
        return "redirect:/admin";
    }

}

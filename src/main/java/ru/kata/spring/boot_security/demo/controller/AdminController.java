package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

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

    @GetMapping("/users")
    public String getAllUsers(Model model, Principal principal) {
        addAttributesToMainPage(model, principal);
        return "admin";
    }

//    @PostMapping()
//    public String addUser(@ModelAttribute("userDto") @Valid UserDto userDto, BindingResult bindingResult,
//                          Model model, Principal principal) {
//        if (userService.existsByUsername(userDto.getUsername())) {
//            bindingResult.rejectValue("username",
//                    "Username already exists", "Username already exists");
//        }
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("hasErrors", true);
//            model.addAttribute("Errors","Username already exists");
//            addAttributesToMainPage(model, principal);
//            return "adminPanel";
//
//        }else {
//            userService.saveUser(userDto);
//            return "redirect:/admin";
//        }
//    }
//
//    @PatchMapping("/{id}")
//    public String updateUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult,
//                             @PathVariable("id") Long id, Model model, Principal principal) {
//        User userByUsername = userService.getUserByUsername(userDto.getUsername());
//        if (userService.uniqueUsername(userDto.getUsername()) && !userByUsername.getId().equals(id)) {
//            bindingResult.rejectValue("username",
//                    "Username already exists", "Username already exists");
//        }
//        if (bindingResult.hasErrors()) {
//            addAttributesToMainPage(model, principal);
//            model.addAttribute("editUserError", true);
//            return "adminPanel";
//        }
//        userService.updateUser(userDto);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUsers(id);
//        return "redirect:/admin";
//    }

    private void addAttributesToMainPage(Model model, Principal principal) {

        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);

        List<User> listOfUsers = userService.getAllUsers();
        model.addAttribute("admin", listOfUsers);

    }
}

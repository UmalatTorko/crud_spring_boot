package ru.gordanov.crud_spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gordanov.crud_spring_boot.entity.User;
import ru.gordanov.crud_spring_boot.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String showUser(Principal principal, Model model) {
        model.addAttribute("user", service.getUserByUsername(principal.getName()));
        return "showUser";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "adminPage";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute("user") User user) {
        service.saveOrUpdate(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "updateUser";
    }

    @PatchMapping("/admin/update")
    public String update(@ModelAttribute("user") User user) {
        service.saveOrUpdate(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") long id) {
        service.delete(id);
        return "redirect:/admin";
    }
}
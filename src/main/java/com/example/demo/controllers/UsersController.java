package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public String getUserPage(@RequestParam(value = "name", required = false) String name, ModelMap model) {
        List<User> users;
        if (name == null) {
            users = usersRepository.findAll();
        } else {
            users = usersRepository.findAllByName(name);
        }
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping(value = "/users")
    public String postUser(User user) {
        usersRepository.save(user);
        return "redirect:/users";
    }

}
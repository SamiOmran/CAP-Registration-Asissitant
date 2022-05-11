package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService usersSerivce;

    public UserController(UserService usersSerivce) {
        this.usersSerivce = usersSerivce;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return usersSerivce.findAll();
    }

    @PostMapping(value = "/register", produces = "application/json")
    public String registerNewStudent(@RequestBody User user) {
        usersSerivce.register(user);
        return "Success";
    }

}

package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.InputMismatchException;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/allUsers", produces = "application/json")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/register", produces = "application/json")
    public String registerNewStudent(@Valid @RequestBody User user, BindingResult bindingResult) throws Exception {
        System.out.println(user);
        if (bindingResult.hasErrors()) {
            throw new InputMismatchException();
        } else {
            return userService.register(user);
        }
    }

}

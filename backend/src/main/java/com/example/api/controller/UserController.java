package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.InputMismatchException;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @RequestMapping(value = "/student", method = RequestMethod.GET)
//    @ResponseBody
//    public User currentUserName(Principal principal) {
//        System.out.println(principal.getName().getClass());
//        return userService.getUserObject(principal.getName());
//    }

    @GetMapping(value = "/")
    public String HomePage() {
        return "index";
    }

    @GetMapping(value = "/mainPage")
    public String Student(){return "student";}

    @GetMapping(value = "/login")
    public String Login() {
        return "index";
    }
//    @PostMapping(value = "/mainPage")
//    public String loginPage() {
//        return "student";
//    }



    @ResponseBody
    @GetMapping(value = "/allUsers", produces = "application/json")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @ResponseBody
    @PostMapping(value = "/register", produces = "application/json")
    public String registerNewStudent(@Valid @RequestBody User user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new InputMismatchException();
        } else {
            return userService.register(user);
        }
    }

}

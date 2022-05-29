package com.example.api.controller;

import com.example.api.model.Role;
import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/student/mainPage")
    public String student(){return "student";}

    @GetMapping(value = "/hod")
    public String headOfDepartment(){return "reset";}

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/")
    public String goHomePage(Principal principal, Model model) {
        Optional<User> user = userService.getUserObject(principal.getName());
        model.addAttribute("user", user);
        Role role;
        if(user.isPresent()){
            role = (user.get().getRoles()).iterator().next();
        }else {
            return null;
        }

        if(role.getName().equals("ROLE_STUDENT")){
            return "redirect:/student/mainPage";
        }
        if(role.getName().equals("ROLE_HOD")) {
            return "redirect:/hod";
        }
        return "/login";
    }


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

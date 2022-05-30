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
    public String headOfDepartment() { return "reset"; }

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

//    @ResponseBody
//    @GetMapping(value = "/allUsers", produces = "application/json")
//    public List<User> getAllUsers() {
//        return userService.findAll();
//    }

    @GetMapping(value = "/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("newStudent", new User());
        return "signup";
    }

    @GetMapping(value = "/register2/{uniNum}/{password}")
    public String getRegistrationPage2(Model model, @PathVariable int uniNum, @PathVariable String password) {
        User newStudent = new User();
        newStudent.setUniversityNumber(uniNum);
        newStudent.setPassword(password);

        model.addAttribute("newStudent", newStudent);
        return "signup2";
    }

    @PostMapping(value = "/student")
    public String registerNewStudent(@Valid @RequestBody User user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new InputMismatchException();
        } else {
            userService.register(user);
            return "student";

        }
    }

}

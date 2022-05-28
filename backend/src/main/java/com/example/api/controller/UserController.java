package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
        return "student";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

//    @PostMapping(value = "/login", produces = "text/html")
//    public String login(@RequestBody long universityNumber,@RequestBody String password, Model model) {
//        try {
//           Optional<User> user = userService.getUser(universityNumber, password);
//           if (user.get().getRole().equals("ROLE_STUDENT")) {
////               model.addAttribute("student", user.get());
//               return "student";
//           } else {
//               return "";
//           }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    @ResponseBody
    @GetMapping(value = "/allUsers", produces = "application/json")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @ResponseBody
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

package com.example.api.controller;

import com.example.api.model.Request;
import com.example.api.model.Role;
import com.example.api.model.User;
import com.example.api.service.RequestService;
import com.example.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final RequestService requestService;

    public UserController(UserService userService, RequestService requestService) {
        this.requestService = requestService;
        this.userService = userService;
    }
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

//    @GetMapping(value = "/student/mainPage")
//    public String student(){
//        return "main-page";
//    }

    @GetMapping(value = "/hod")
    public String headOfDepartment(){return "reset";}

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/")
    public String goHomePage(Principal principal, Model model) {
        Optional<User> user = userService.getUserObject(principal.getName());
        Role role;
        if(user.isPresent()){
            role = (user.get().getRoles()).iterator().next();
        } else {
            return null;
        }
        if(role.getName().equals("ROLE_STUDENT")) {
            model.addAttribute("user", user.get());
            model.addAttribute("request", new Request());
            return "student/main-page";
        }
        if(role.getName().equals("ROLE_HOD")) {
            model.addAttribute("hod", user.get());
            model.addAttribute("requests", user.get().getRequests());
            logger.info(user.get().getRequests().size() + "");
            return "HOD/hod-notification";
        }
        return "/student/main-page";
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

    @GetMapping(value = "/request/details/{requestId}")
    public String getRequestDetails(Model model, Principal principal, @PathVariable Long requestId) {
        Optional<User> user = userService.getUserObject(principal.getName());
        model.addAttribute("user", user.get());
        Request request = requestService.getRequest(requestId);
        model.addAttribute("request", request);
        return "HOD/request-details";
    }

    @GetMapping(value = "/personal/info")
    public String getPersonalInfo(Principal principal, Model model) {
        Optional<User> user = userService.getUserObject(principal.getName());
        model.addAttribute("user", user.get());
        return "HOD/hod-personal-info";
    }

}

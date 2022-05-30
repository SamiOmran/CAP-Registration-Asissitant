package com.example.api.controller;

import com.example.api.model.Request;
import com.example.api.model.User;
import com.example.api.service.RequestService;
import com.example.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class RequestController {

    private final RequestService requestService;
    private UserService userService;
    private final Logger logger = LoggerFactory.getLogger(RequestController.class);

    public RequestController(RequestService requestService, UserService userService) {
        this.userService = userService;
        this.requestService = requestService;
    }

    @PostMapping(value = "/request", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, "application/x-www-form-urlencoded"})
    public String sendRequest(@Valid Request request, Principal principal) {
        Optional<User> user = userService.getUserObject(principal.getName());
        requestService.sendRequest(request, user.get());
        return "redirect:/";
    }
}

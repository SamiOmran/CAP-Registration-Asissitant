package com.example.api.service;

import com.example.api.model.User;
import com.example.api.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private static RoleService rolesService;
    private final BCryptPasswordEncoder encoder;

    UserService(UserRepo userRepo, RoleService rolesService) {
        this.userRepo = userRepo;
        this.rolesService = rolesService;
        encoder = new BCryptPasswordEncoder();
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public String save(User newUser) {
        userRepo.save(newUser);
        return "Success";
    }

    public String register(User newUser) {
        String secret = "{bcrypt}" + encoder.encode(newUser.getPassword());
        newUser.setPassword(secret);
        newUser.setRole(rolesService.findByName("ROLE_STUDENT"));
        newUser.setFullName(newUser.getFname() + newUser.getLname());
        return save(newUser);
    }

}

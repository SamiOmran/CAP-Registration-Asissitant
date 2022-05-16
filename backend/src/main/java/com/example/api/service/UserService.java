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

    private boolean emailExist(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    public String save(User newUser) {
        userRepo.save(newUser);
        return "Success";
    }

    public String register(User newUser) throws Exception {
        try {
            if (emailExist(newUser.getEmail())) {
                throw new Exception("Email already exists");
            }

            String secret = "{bcrypt}" + encoder.encode(newUser.getPassword());
            newUser.setPassword(secret);
            newUser.setRole(rolesService.findByName("ROLE_STUDENT"));
            newUser.setFullName(newUser.getFname() + newUser.getLname());
            return save(newUser);
        } catch (Exception exception) {
            throw new Exception(exception);
        }

    }

    public String login(String email, String password) {
        User user = new User();
        if(emailExist(email)){
            user = userRepo.findByEmail(email).get();
        }
        return "";
    }

}

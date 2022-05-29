package com.example.api.service;

import com.example.api.model.User;
import com.example.api.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private static RoleService rolesService;
    private final BCryptPasswordEncoder encoder;

    UserService(UserRepo userRepo, RoleService rolesService) {
        this.userRepo = userRepo;
        UserService.rolesService = rolesService;
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
            newUser.addRole(rolesService.findByName("ROLE_STUDENT"));
            newUser.setEnabled(true);
            newUser.setFullName(newUser.getFname() + newUser.getLname());
            return save(newUser);
        } catch (Exception exception) {
            throw new Exception(exception);
        }

    }

    public Optional<User> getUser(long universityNumber, String password) throws Exception {
       Optional<User> optionalUser = userRepo.findByUniversityNumber(universityNumber);
       if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
           return optionalUser;
       }
       throw new Exception("User not found");
    }

    public Optional<User> getUserObject(String username) {
        long universityNumber = Long.parseLong(username);
        return userRepo.findByUniversityNumber(universityNumber);
    }

}

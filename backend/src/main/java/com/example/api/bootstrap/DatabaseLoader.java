package com.example.api.bootstrap;

import com.example.api.model.Role;
import com.example.api.model.User;
import com.example.api.repository.RoleRepo;
import com.example.api.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final RoleRepo roleRepository;
    private final UserRepo userRepository;

    public DatabaseLoader(RoleRepo roleRepository, UserRepo userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        addUsersRoles();
    }

    private void addUsersRoles() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = "{bcrypt}" + encoder.encode("password");

        Role studentRole = new Role("ROLE_STUDENT");
        roleRepository.save(studentRole);
        User STUDENT = new User(11828579, "Sami", "Imran", "Sami Imran", "sami@imran.com", "123456", studentRole);
        userRepository.save(STUDENT);

        Role headOfDepartmentRole = new Role("ROLE_HOD");
        roleRepository.save(headOfDepartmentRole);
        User HOD = new User(1313, "Hamed", "Abdelhaq", "Hamed abdelahq", "hamed@najah.edu", "123456",headOfDepartmentRole);
        userRepository.save(HOD);

        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);
        User systemAdmin = new User(0070, "admin", "admin","admin admin", "sami.imran@stu.najah.edu","123123123",adminRole);
        userRepository.save(systemAdmin);


        Long numberOfUsers = userRepository.count();
        System.out.println(numberOfUsers + " users have just inserted in the system");
    }
}

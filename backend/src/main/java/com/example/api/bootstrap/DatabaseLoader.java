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
        User STUDENT1 = new User(11828579, "Jana", "Khalili", "Hala@gmail.com", secret, true);
        STUDENT1.addRole(studentRole);
        userRepository.save(STUDENT1);

        User STUDENT4 = new User(11834534, "Hala", "Salah", "Hala.Q@gmail.com", secret, true);
        STUDENT4.addRole(studentRole);
        userRepository.save(STUDENT4);

        User STUDENT2 = new User(11822865, "Abdallah", "Qawas", "abdallah@gmail.com", secret, true);
        STUDENT2.addRole(studentRole);
        userRepository.save(STUDENT2);

        User STUDENT3 = new User(11820884, "Sami", "Imran", "sami@gmail.com", secret, true);
        STUDENT3.addRole(studentRole);
        userRepository.save(STUDENT3);

        Role headOfDepartmentRole = new Role("ROLE_HOD");
        roleRepository.save(headOfDepartmentRole);
        User HOD = new User(1313, "Hamed", "Abdelhaq", "hamed@najah.edu", secret, true);
        HOD.addRole(headOfDepartmentRole);
        userRepository.save(HOD);

        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);
        User systemAdmin = new User(5570, "admin", "admin", "sami.imran@stu.najah.edu", secret, true);
        systemAdmin.addRole(adminRole);
        userRepository.save(systemAdmin);


        Long numberOfUsers = userRepository.count();
        System.out.println(numberOfUsers + " users have just inserted in the system");
    }
}

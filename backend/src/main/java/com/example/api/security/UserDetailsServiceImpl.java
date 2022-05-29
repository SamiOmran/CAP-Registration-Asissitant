package com.example.api.security;

import com.example.api.model.User;
import com.example.api.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepository;

    public UserDetailsServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String univNumber) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUniversityNumber(Long.parseLong(univNumber));
        if( !user.isPresent() ){
            throw new UsernameNotFoundException(univNumber);
        }
        return user.get();

    }
}

package com.example.hospital_management.service;

import com.example.hospital_management.entity.User;
import com.example.hospital_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository ,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user){

        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);

    }

    public User loginUser(String username, String password) {

        System.out.println("Login username = " + username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return user;
    }
}

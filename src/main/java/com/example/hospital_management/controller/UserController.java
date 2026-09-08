package com.example.hospital_management.controller;

import com.example.hospital_management.dto.LoginRequest;
import com.example.hospital_management.dto.LoginResponse;
import com.example.hospital_management.entity.User;
import com.example.hospital_management.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {

        return userService.loginUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
    }
}
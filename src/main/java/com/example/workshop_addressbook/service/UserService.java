package com.example.workshop_addressbook.service;

import com.example.workshop_addressbook.dto.UserDTO;
import com.example.workshop_addressbook.model.User;
import com.example.workshop_addressbook.repository.UserRepository;
import com.example.workshop_addressbook.security.JwtUtil;
import com.example.workshop_addressbook.security.PasswordEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoderService passwordEncoderService;

    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoderService.encodePassword(userDTO.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty() || !passwordEncoderService.matches(password, userOpt.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtUtil.generateToken(email);
    }
}
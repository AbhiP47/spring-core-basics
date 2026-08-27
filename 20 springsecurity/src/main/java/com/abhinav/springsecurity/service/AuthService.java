package com.abhinav.springsecurity.service;

import com.abhinav.springsecurity.dto.UserRegisterRequestDto;
import com.abhinav.springsecurity.dto.UserRegisterResponseDto;
import com.abhinav.springsecurity.entity.Role;
import com.abhinav.springsecurity.entity.User;
import com.abhinav.springsecurity.repository.RoleRepository;
import com.abhinav.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto) {
        User user = new User();

        user.setUsername(requestDto.getUsername());
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        user.setPassword(encodedPassword);
        Role role = roleRepository.findByName("ROLE_USER").get();

        user.getRoles().add(role);

        userRepository.save(user);

        return UserRegisterResponseDto.builder().username(user.getUsername())
                .message("User Registration Successfull").build();
    }
}

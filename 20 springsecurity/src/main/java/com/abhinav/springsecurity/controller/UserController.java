package com.abhinav.springsecurity.controller;

import com.abhinav.springsecurity.dto.UserRegisterRequestDto;
import com.abhinav.springsecurity.dto.UserRegisterResponseDto;
import com.abhinav.springsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/hello")
    public String sayHello(Authentication authentication)
    {
        return "Hello, you are logged in as : " + authentication.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(
            @RequestBody UserRegisterRequestDto requestDto
            )
    {
        UserRegisterResponseDto responseDto = authService.registerUser(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}

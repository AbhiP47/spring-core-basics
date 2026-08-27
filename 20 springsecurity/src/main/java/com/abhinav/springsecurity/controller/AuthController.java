package com.abhinav.springsecurity.controller;

import com.abhinav.springsecurity.dto.LoginRequestDTO;
import com.abhinav.springsecurity.dto.LoginResponseDTO;
import com.abhinav.springsecurity.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO loginRequestDTO
    )
    {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()
                );
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        String token = jwtService.generateToken(authentication);
        return new LoginResponseDTO(token);
    }
}

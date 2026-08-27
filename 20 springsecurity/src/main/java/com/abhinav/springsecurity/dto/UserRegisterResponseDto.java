package com.abhinav.springsecurity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterResponseDto {

    private String username;
    private String message;
}

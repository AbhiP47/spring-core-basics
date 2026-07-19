package com.abhinav.springBootFilters.dto;

public class StudentResponseDto {
    private  String name;
    private String  message;

    public String getName() {
        return name;
    }

    public StudentResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public StudentResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
}

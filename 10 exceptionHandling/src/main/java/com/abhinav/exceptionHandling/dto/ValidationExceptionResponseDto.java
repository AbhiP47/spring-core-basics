package com.abhinav.exceptionHandling.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationExceptionResponseDto {

    private LocalDateTime timeStamp;
    private  int statusCode;
    private String error;
    private String message;
    private String path;
    private Map<String, String> fieldErrors;

    public ValidationExceptionResponseDto(LocalDateTime timeStamp, int statusCode, String error, String message, String path, Map<String, String> fieldErrors) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }
}

package com.abhinav.interceptors.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest ,
                             HttpServletResponse httpServletResponse,
                             Object handler)
    {
        String apiKey = httpServletRequest.getHeader("x-api-key");

        if(apiKey != null && !apiKey.equals("secret123"))
        {
            return false;
        }
        return true;
    }
}

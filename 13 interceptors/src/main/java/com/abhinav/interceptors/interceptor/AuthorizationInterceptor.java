package com.abhinav.interceptors.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest ,
                             HttpServletResponse httpServletResponse,
                             Object handler) throws IOException {
        String userRole = httpServletRequest.getHeader("x-user-role");

        if(userRole != null && !userRole.equals("ADMIN"))
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.getWriter().write("{\n" +
                    "    \"message\" : \"You are not authorized\"\n" +
                    "}");
            return false;
        }
        return true;
    }
}

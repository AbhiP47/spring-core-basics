package com.abhinav.filters.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class ApiResponseTime implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        long startTime = System.currentTimeMillis();


        try
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }

        finally {
            long duration = System.currentTimeMillis() - startTime;

            System.out.println("Latency For " + httpServletRequest.getRequestURI() + " is " + duration + " miliSeconds");
        }

    }
}

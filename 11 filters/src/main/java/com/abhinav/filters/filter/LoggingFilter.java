package com.abhinav.filters.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

//        System.out.println("Request Entered in Logging Filter");
//
//        filterChain.doFilter(servletRequest,servletResponse);
//
//        System.out.println("Request Exiting from Logging Filter");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        System.out.println("Incoming Request" + httpServletRequest.getMethod()
        + " " + httpServletRequest.getRequestURI());

        filterChain.doFilter(servletRequest , servletResponse);

        System.out.println("Response Status : " +
                 httpServletResponse.getStatus());

    }

}

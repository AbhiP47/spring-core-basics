package com.abhinav.filters.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestId = UUID.randomUUID().toString();
        httpServletResponse.setHeader("X-Request-ID",requestId);  // Right

        System.out.println("Incoming Request" + httpServletRequest.getMethod()
                + " " + httpServletRequest.getRequestURI());

        filterChain.doFilter(servletRequest , servletResponse);

//        httpServletResponse.setHeader("X-Request-ID",requestId);  // Wrong

        // We cannot set the header here because after our request comes back from the controller to the filters,
        // we cannot change the response.
        // It is read only. So we set the header only before our request goes to the filters.
        System.out.println("Response Status : " +
                httpServletResponse.getStatus());

    }

}


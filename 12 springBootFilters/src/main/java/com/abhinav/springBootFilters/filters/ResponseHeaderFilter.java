package com.abhinav.springBootFilters.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Order(1)
@Component
public class ResponseHeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String uri = httpServletRequest.getRequestURI();

        if(!uri.startsWith("/api/"))
        {
            filterChain.doFilter(servletRequest,servletResponse);
        }

        String id = UUID.randomUUID().toString();
        httpServletResponse.setHeader("x-request-id",id);

        filterChain.doFilter(servletRequest,servletResponse);

    }
}

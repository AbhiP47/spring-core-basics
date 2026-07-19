package com.abhinav.springBootFilters.filters;

import jakarta.servlet.*;

import java.io.IOException;

public class DummyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Dummy Filter");
    }
}

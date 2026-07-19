package com.abhinav.springBootFilters.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SpringDemoFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
    {
        String uri = request.getRequestURI();
        return !uri.startsWith("/api/");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    }
}
// Once per request filter is provided by the spring MVC and  not servlets and it is the same as the
// filter implementation but here we do not need to type Cast servlet request to HTTP servlet request.
// It also provides us a method should_not_filter, which gives us a functionality not to run the filter for a specific condition. 
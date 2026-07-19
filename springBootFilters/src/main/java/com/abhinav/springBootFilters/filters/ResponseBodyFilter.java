package com.abhinav.springBootFilters.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Order(2)
public class ResponseBodyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        filterChain.doFilter(servletRequest,responseWrapper);

        byte[] originalBodyBytes = responseWrapper.getContentAsByteArray();
        String originalBody = new String(originalBodyBytes);
        String modifiedBody =
                """
                {
                    "originalResponse" : %s,
                    "appName" : "Student Management System"
                }
                        """.formatted(originalBody); // Java Text Block

        responseWrapper.resetBuffer(); // Delete the response obtained from the controller.
        responseWrapper.getWriter().write(modifiedBody);
        responseWrapper.copyBodyToResponse(); // Copies response wrapper to the actual servlet response.
    }
}

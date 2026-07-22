package com.abhinav.interceptors.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
   public boolean preHandle(HttpServletRequest request,
                            HttpServletResponse response,
                            Object handler) throws Exception {

        if(handler instanceof HandlerMethod handlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            String controllerName = method.getBeanType().getName();
            String methodName = method.getMethod().getName();

            System.out.println("Pre Handled Called");
            System.out.println("Controller name " + controllerName);
            System.out.println("Method name " + methodName);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("Post handle called");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) throws Exception {
        System.out.println("After Completion called");
    }
}

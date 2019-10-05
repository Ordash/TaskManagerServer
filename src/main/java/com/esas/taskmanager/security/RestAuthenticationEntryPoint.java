package com.esas.taskmanager.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        String json = String.format("{\"message\": \"%s\"}", e.getMessage());
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(json);
    }
}
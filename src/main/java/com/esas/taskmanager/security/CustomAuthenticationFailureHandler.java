package com.esas.taskmanager.security;

import com.esas.taskmanager.util.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if (exception instanceof UsernameNotFoundException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            mapper.writeValue(response.getWriter(), ErrorResponse.of(exception.getMessage(), HttpStatus.BAD_REQUEST));
        } else if (exception instanceof BadCredentialsException) {
            mapper.writeValue(response.getWriter(), ErrorResponse.of(exception.getMessage(), HttpStatus.UNAUTHORIZED));
        }
    }
}
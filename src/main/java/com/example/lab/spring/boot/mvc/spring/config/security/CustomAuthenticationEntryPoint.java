package com.example.lab.spring.boot.mvc.spring.config.security;

import com.example.lab.spring.boot.mvc.spring.config.security.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Handling unauthenticated request in security filter chain
 */
@Slf4j
@Component
class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        SecurityContextHolder.setContext(context);
        log.debug("Unauthenticated: {}", e.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        String code = "-1";
        if (e instanceof InvalidTokenException ex) {
            code = ex.getErrorCode().getCode();
        }
        response.getOutputStream().println("{\"data\": null, \"status\": {\"code\": \"" + code + "\", \"message\": \"" + e.getMessage() + "\"}}");
    }
}

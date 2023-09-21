package com.yugen.springbootrestapi.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JwtAuthenticationEntryPoint is a component that provides an entry point for JWT authentication.
 * It handles AuthenticationException by sending an unauthorized error response.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * This method is invoked when there is an AuthenticationException.
     *
     * @param request       The HttpServletRequest.
     * @param response      The HttpServletResponse.
     * @param authException The AuthenticationException.
     * @throws IOException      If an input or output exception occurred.
     * @throws ServletException If a servlet exception occurred.
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}

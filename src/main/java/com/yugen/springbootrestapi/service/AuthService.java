package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.dto.LoginDto;
import com.yugen.springbootrestapi.payload.dto.RegisterDto;

/**
 * AuthService is an interface for authentication services.
 * It contains methods for login and registration functionality.
 */
public interface AuthService {

    /**
     * This method is used for login functionality.
     *
     * @param loginDto A data transfer object containing login credentials.
     * @return A string response after successful login.
     */
    String login(LoginDto loginDto);

    /**
     * This method is used for registration functionality.
     *
     * @param registerDto A data transfer object containing registration information.
     * @return A string response after successful registration.
     */
    String register(RegisterDto registerDto);
}

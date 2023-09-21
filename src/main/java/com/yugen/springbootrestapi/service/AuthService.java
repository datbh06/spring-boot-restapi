package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.dto.LoginDto;

/**
 * AuthService is an interface for authentication services.
 * It contains methods for login.
 */
public interface AuthService {

    /**
     * This method is used for login functionality.
     *
     * @param loginDto A data transfer object containing login credentials.
     * @return A string response after successful login.
     */
    String login(LoginDto loginDto);
}

package com.yugen.springbootrestapi.controller;

import com.yugen.springbootrestapi.payload.dto.LoginDto;
import com.yugen.springbootrestapi.payload.dto.RegisterDto;
import com.yugen.springbootrestapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController is a REST controller for authentication services.
 * It contains endpoints for login and registration.
 */
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    /**
     * The AuthService to handle login and registration.
     */
    private AuthService authService;

    /**
     * This endpoint is used for login functionality.
     *
     * @param loginDto A data transfer object containing login credentials.
     * @return A ResponseEntity with a string response after successful login.
     */
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    /**
     * This endpoint is used for registration functionality.
     *
     * @param registerDto A data transfer object containing registration information.
     * @return A ResponseEntity with a string response after successful registration.
     */
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

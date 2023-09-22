package com.yugen.springbootrestapi.controller;

import com.yugen.springbootrestapi.payload.dto.LoginDto;
import com.yugen.springbootrestapi.payload.dto.RegisterDto;
import com.yugen.springbootrestapi.payload.response.JwtAuthResponse;
import com.yugen.springbootrestapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication RestAPI", description = "Authentication RestAPI")
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
    @Operation(
            summary = "Login RestAPI",
            description = "Login REST API is used to authenticate user credentials and generate JWT Token"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    /**
     * This endpoint is used for registration functionality.
     *
     * @param registerDto A data transfer object containing registration information.
     * @return A ResponseEntity with a string response after successful registration.
     */
    @Operation(
            summary = "Register RestAPI",
            description = "Register REST API is used to register a new user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

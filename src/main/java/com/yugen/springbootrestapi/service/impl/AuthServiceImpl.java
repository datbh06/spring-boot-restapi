package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Role;
import com.yugen.springbootrestapi.entity.User;
import com.yugen.springbootrestapi.exception.BlogAPIException;
import com.yugen.springbootrestapi.payload.dto.LoginDto;
import com.yugen.springbootrestapi.payload.dto.RegisterDto;
import com.yugen.springbootrestapi.repository.RoleRepository;
import com.yugen.springbootrestapi.repository.UserRepository;
import com.yugen.springbootrestapi.security.JwtTokenProvider;
import com.yugen.springbootrestapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * AuthServiceImpl is an implementation of the AuthService interface.
 * It contains methods for login functionality.
 */
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public String login(LoginDto loginDto) {

        // Authenticate the LoginDto
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                ));

        // Set the authenticated user in the SecurityContext
        SecurityContextHolder // This is a helper class to access the security context
                .getContext()
                .setAuthentication(authentication);

        // Generate the JWT token

        // Return a success message
        return jwtTokenProvider.generateToken(authentication);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String register(RegisterDto registerDto) {

        // check if username exists
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // check if email exists
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        // create a new user
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // set role for the user
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRoleOptional = roleRepository.findByName("ROLE_USER");

        if (userRoleOptional.isPresent()) {
            Role userRole = userRoleOptional.get();
            roles.add(userRole);
            user.setRoles(roles);
        } else {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Role not found!");
        }

        // save the user
        userRepository.save(user);

        return "User registered successfully!.";
    }
}

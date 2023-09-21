package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.payload.dto.LoginDto;
import com.yugen.springbootrestapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                ));

        SecurityContextHolder // This is a helper class to access the security context
                .getContext()
                .setAuthentication(authentication);


        return "User logged in successfully";
    }
}

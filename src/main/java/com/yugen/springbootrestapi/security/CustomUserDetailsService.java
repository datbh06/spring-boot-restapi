package com.yugen.springbootrestapi.security;

import com.yugen.springbootrestapi.entity.User;
import com.yugen.springbootrestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a custom implementation of
 * the UserDetailsService interface for managing user authentication.
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads a user by their username or email.
     *
     * @param usernameOrEmail the username or email of the user
     * @return the UserDetails object representing the user
     * @throws UsernameNotFoundException if the user is not found by their username or email
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);
    }
}

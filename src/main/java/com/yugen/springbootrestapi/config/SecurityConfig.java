package com.yugen.springbootrestapi.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class represents the security configuration for the BlogAPI.
 */
@Configuration
@EnableMethodSecurity
//@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object
     * @return the configured SecurityFilterChain object
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                                .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Creates a PasswordEncoder bean.
     *
     * @return the PasswordEncoder bean
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an AuthenticationManager bean.
     *
     * @param configuration the AuthenticationConfiguration object
     * @return the AuthenticationManager bean
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}

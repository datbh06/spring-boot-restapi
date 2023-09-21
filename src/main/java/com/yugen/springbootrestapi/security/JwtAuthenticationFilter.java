package com.yugen.springbootrestapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthenticationFilter is a filter for JWT authentication.
 * It validates the JWT token from the request and sets the authentication in the SecurityContext.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * The JwtTokenProvider to generate and validate JWT tokens.
     */
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * The UserDetailsService to load user details.
     */
    private final UserDetailsService userDetailsService;

    /**
     * Constructs a JwtAuthenticationFilter with the given JwtTokenProvider and UserDetailsService.
     *
     * @param jwtTokenProvider   The JwtTokenProvider to generate and validate JWT tokens.
     * @param userDetailsService The UserDetailsService to load user details.
     */
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    /**
     * This method is invoked for each request. It validates the JWT token from the request
     * and sets the authentication in the SecurityContext.
     *
     * @param request     The HttpServletRequest.
     * @param response    The HttpServletResponse.
     * @param filterChain The FilterChain.
     * @throws ServletException If a servlet exception occurred.
     * @throws IOException      If an input or output exception occurred.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // get JWT token from http request
        String token = getTokenFromRequest(request);

        // validate token
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {

            // get username from token
            String username = jwtTokenProvider.getUsername(token);

            // load the user associated with token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }
        filterChain.doFilter(request, response);
    }

    /**
     * This method retrieves the JWT token from the request header.
     *
     * @param request The HttpServletRequest.
     * @return The JWT token string from the request header, or null if not present or not valid.
     */
    private String getTokenFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

package com.yugen.springbootrestapi.security;

import com.yugen.springbootrestapi.exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * JwtTokenProvider is a component that provides JWT token services.
 * It contains methods for generating, parsing, and validating JWT tokens.
 */
@Component
public class JwtTokenProvider {

    /**
     * The secret key for signing JWT tokens.
     */
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    /**
     * The expiration date for JWT tokens in milliseconds.
     */
    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    /**
     * This method generates a JWT token for an authenticated user.
     *
     * @param authentication The Authentication object containing the user's details.
     * @return A JWT token string.
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    /**
     * This method generates a signing key for JWT tokens.
     *
     * @return A Key object for signing JWT tokens.
     */
    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    /**
     * This method retrieves the username from a JWT token.
     *
     * @param token The JWT token string.
     * @return The username string from the JWT token.
     */
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /**
     * This method validates a JWT token and throws an exception if the token is invalid.
     *
     * @param token The JWT token string to validate.
     * @return true if the token is valid, otherwise throws an exception.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }
}

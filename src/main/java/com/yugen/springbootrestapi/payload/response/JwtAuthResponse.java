package com.yugen.springbootrestapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JwtAuthResponse is a data transfer object for JWT authentication response.
 * It contains an access token and a token type.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {

    /**
     * The access token string.
     */
    private String accessToken;

    /**
     * The type of the token. Default is "Bearer".
     */
    private String tokenType = "Bearer";
}

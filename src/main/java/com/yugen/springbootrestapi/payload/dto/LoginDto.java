package com.yugen.springbootrestapi.payload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginDto is a data transfer object for login information.
 * It contains username or email and password for login.
 */
@Schema(description = "Login Request Payload")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    /**
     * This can be either a username or an email used for login.
     */
    @Schema(description = "Username or Email")
    private String usernameOrEmail;

    /**
     * The password used for login.
     */
    @Schema(description = "Password")
    private String password;
}

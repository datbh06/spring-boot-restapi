package com.yugen.springbootrestapi.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginDto is a data transfer object for login information.
 * It contains username or email and password for login.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    /**
     * This can be either a username or an email used for login.
     */
    private String usernameOrEmail;

    /**
     * The password used for login.
     */
    private String password;
}

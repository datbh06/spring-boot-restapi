package com.yugen.springbootrestapi.payload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RegisterDto is a data transfer object for registration information.
 * It contains name, username, email, and password for registration.
 */
@Schema(
        description = "Data Transfer Object for registration information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    /**
     * The name of the user registering.
     */
    @Schema(
            description = "Name of the user registering"
    )
    private String name;

    /**
     * The username chosen by the user for registration.
     */
    @Schema(
            description = "Username chosen by the user for registration"
    )
    private String username;

    /**
     * The email provided by the user for registration.
     */
    @Schema(
            description = "Email provided by the user for registration"
    )
    private String email;

    /**
     * The password chosen by the user for registration.
     */
    @Schema(
            description = "Password chosen by the user for registration"
    )
    private String password;
}

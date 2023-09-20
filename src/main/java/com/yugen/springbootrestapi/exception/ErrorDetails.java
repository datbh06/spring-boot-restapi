package com.yugen.springbootrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Represents the details of an error.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    /**
     * The time when the error occurred.
     */
    private Date timestamp;

    /**
     * The error message.
     */
    private String message;

    /**
     * Additional details about the error.
     */
    private String details;
}

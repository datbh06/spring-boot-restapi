package com.yugen.springbootrestapi.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Represents a Data Transfer Object for the Comment entity.
 */
@Data
public class CommentDto {
    /**
     * Unique identifier of the comment.
     */
    private Long id;

    /**
     * Name of the commenter.
     */
    @NotEmpty(message = "Name is required")
    private String name;

    /**
     * Email of the commenter.
     */
    @NotEmpty(message = "Email is required")
    @Email(message = "Email must be valid email address format (e.g. abc@gmail.com)")
    private String mail;

    /**
     * Body of the comment.
     */
    @NotEmpty(message = "Body is required")
    @Size(min = 10, message = "Body must have at least 10 characters")
    private String body;

}

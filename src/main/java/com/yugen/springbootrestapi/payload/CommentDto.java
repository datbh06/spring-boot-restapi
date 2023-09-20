package com.yugen.springbootrestapi.payload;

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
    private String name;

    /**
     * Email of the commenter.
     */
    private String mail;

    /**
     * Body of the comment.
     */
    private String body;

}

package com.yugen.springbootrestapi.payload;

import lombok.Data;

/**
 * Represents a Data Transfer Object for the Post entity.
 */
@Data
public class PostDto {

    /**
     * Unique identifier of the post.
     */
    private Long id;

    /**
     * Title of the post.
     */
    private String title;

    /**
     * Description of the post.
     */
    private String description;

    /**
     * Content of the post.
     */
    private String content;
}

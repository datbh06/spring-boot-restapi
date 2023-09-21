package com.yugen.springbootrestapi.payload.dto;

import com.yugen.springbootrestapi.payload.dto.CommentDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

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
    @NotEmpty(message = "Title is required")
    @Size(min = 2, message = "Title must have at least 2 characters")
    private String title;

    /**
     * Description of the post.
     */
    @NotEmpty(message = "Description is required")
    @Size(min = 10, message = "Description must have at least 10 characters")
    private String description;

    /**
     * Content of the post.
     */
    @NotEmpty(message = "Content is required")
    @Size(min = 10, message = "Content must have at least 10 characters")
    private String content;

    /**
     * Set of comments for the post.
     */
    private Set<CommentDto> comments;

    /**
     * The category ID of the post.
     */
    private Long categoryId;
}

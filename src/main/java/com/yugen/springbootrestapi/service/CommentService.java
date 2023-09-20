package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.CommentDto;

/**
 * Service interface for managing comments.
 */
public interface CommentService {

    /**
     * Creates a new comment for a specific post.
     *
     * @param postId     the ID of the post to which the comment belongs
     * @param commentDto the data transfer object containing the details of the comment
     * @return the created comment
     */
    CommentDto createComment(Long postId, CommentDto commentDto);
}

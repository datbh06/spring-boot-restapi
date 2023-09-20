package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.CommentDto;

import java.util.List;

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

    /**
     * Retrieves all comments for a specific post.
     *
     * @param postId the ID of the post to which the comments belong
     * @return a list of all comments for the given post
     */
    List<CommentDto> getCommentsByPostId(Long postId);
}

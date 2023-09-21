package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.dto.CommentDto;

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

    /**
     * Retrieves a comment by its id.
     *
     * @param commentId the id of the comment to retrieve
     * @param postId    the id of the post to which the comment belongs
     * @return the retrieved comment
     */
    CommentDto getCommentById(Long postId, Long commentId);

    /**
     * Updates a comment.
     *
     * @param postId     the id of the post to which the comment belongs
     * @param commentId  the id of the comment to update
     * @param commentDto the data transfer object containing the details of the comment
     * @return the updated comment
     */
    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

    /**
     * Deletes a comment.
     *
     * @param postId    the id of the post to which the comment belongs
     * @param commentId the id of the comment to delete
     */
    void deleteComment(Long postId, Long commentId);
}

package com.yugen.springbootrestapi.controller;

import com.yugen.springbootrestapi.payload.CommentDto;
import com.yugen.springbootrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing comments.
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {

    /**
     * Service for managing comments.
     */
    private CommentService commentService;

    /**
     * Creates a new comment for a specific post.
     *
     * @param commentDto the data transfer object containing the details of the comment
     * @param postId     the ID of the post to which the comment belongs
     * @return the created comment
     */
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable(value = "postId") Long postId) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves all comments for a specific post.
     *
     * @param postId the ID of the post to which the comments belong
     * @return a list of all comments for the given post
     */
    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    /**
     * Retrieves a comment by its id.
     *
     * @param postId    the id of the post to which the comment belongs
     * @param commentId the id of the comment to retrieve
     * @return the retrieved comment
     */
    @GetMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    /**
     * Updates a comment.
     *
     * @param postId     the id of the post to which the comment belongs
     * @param commentId  the id of the comment to update
     * @param commentDto the data transfer object containing the details of the comment
     * @return the updated comment
     */
    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId,
            @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    /**
     * Deletes a comment.
     *
     * @param postId    the id of the post to which the comment belongs
     * @param commentId the id of the comment to delete
     */
    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}

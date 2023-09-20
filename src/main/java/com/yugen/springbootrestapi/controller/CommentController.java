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
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
    }
}

package com.yugen.springbootrestapi.controller;

import com.yugen.springbootrestapi.payload.dto.CommentDto;
import com.yugen.springbootrestapi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing comments.
 */
@Tag(name = "CRUD RestAPI for Comment Resource")
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
    @Operation(
            summary = "Create a new comment RestAPI",
            description = "Create Comment REST API is used to save comment into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @Valid @RequestBody CommentDto commentDto,
            @PathVariable(value = "postId") Long postId) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves all comments for a specific post.
     *
     * @param postId the ID of the post to which the comments belong
     * @return a list of all comments for the given post
     */
    @Operation(
            summary = "Get all comments by post id RestAPI",
            description = "Get all comments by post id REST API is used to retrieve all comments for a specific post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
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
    @Operation(
            summary = "Get comment by id RestAPI",
            description = "Get comment by id REST API is used to retrieve a comment by its id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
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
    @Operation(
            summary = "Update comment RestAPI",
            description = "Update comment REST API is used to update a comment"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId,
            @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    /**
     * Deletes a comment.
     *
     * @param postId    the id of the post to which the comment belongs
     * @param commentId the id of the comment to delete
     */
    @Operation(
            summary = "Delete comment RestAPI",
            description = "Delete comment REST API is used to delete a comment"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 OK"
    )
    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}

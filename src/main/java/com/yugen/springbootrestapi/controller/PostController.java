package com.yugen.springbootrestapi.controller;

import com.yugen.springbootrestapi.payload.dto.PostDto;
import com.yugen.springbootrestapi.payload.response.PostResponse;
import com.yugen.springbootrestapi.service.PostService;
import com.yugen.springbootrestapi.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing posts.
 */
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Tag(name = "CRUD RestAPI for Post Resource")
public class PostController {

    /**
     * Service for managing posts.
     */
    private final PostService postService;

    /**
     * Creates a new post.
     *
     * @param postDto the data transfer object containing the details of the post
     * @return the created post
     */
    @Operation(
            summary = "Create a new post RestAPI",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "bearerAuth"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves all posts.
     *
     * @return a list of all posts
     */
    @Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to fetch all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_ORDER, required = false) String sortOrder) {

        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortOrder));
    }

    /**
     * Retrieves a post by its id.
     *
     * @param id the id of the post to retrieve
     * @return the post with the given id
     */
    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    /**
     * Updates a post.
     *
     * @param id      the id of the post to update
     * @param postDto the data transfer object containing the details of the post
     * @return the updated post
     */
    @Operation(
            summary = "update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "bearerAuth"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(postDto, id));
    }

    /**
     * Deletes a post.
     *
     * @param id the id of the post to delete
     */
    @Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "bearerAuth"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    /**
     * Retrieves all posts by category.
     *
     * @param categoryId the id of the category
     * @return a list of all posts by category
     */
    @Operation(
            summary = "Get Posts By Category REST API",
            description = "Get Posts By Category REST API is used to fetch all the posts by category from the database")
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId) {
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }
}

package com.yugen.springbootrestapi.controller;

import com.yugen.springbootrestapi.payload.dto.PostDto;
import com.yugen.springbootrestapi.payload.response.PostResponse;
import com.yugen.springbootrestapi.service.PostService;
import com.yugen.springbootrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing posts.
 */
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
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
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

}

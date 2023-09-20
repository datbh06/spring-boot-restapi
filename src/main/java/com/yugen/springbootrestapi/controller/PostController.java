package com.yugen.springbootrestapi.controller;

import com.yugen.springbootrestapi.payload.PostDto;
import com.yugen.springbootrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    /**
     * Retrieves all posts.
     *
     * @return a list of all posts
     */
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
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


}

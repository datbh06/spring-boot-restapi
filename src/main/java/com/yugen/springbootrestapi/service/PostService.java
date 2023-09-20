package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.PostDto;

import java.util.List;

/**
 * Service interface for managing posts.
 */
public interface PostService {

    /**
     * Creates a new post.
     *
     * @param postDto the data transfer object containing the details of the post
     * @return the created post
     */
    PostDto createPost(PostDto postDto);

    /**
     * Retrieves all posts.
     *
     * @return a list of all posts
     */
    List<PostDto> getAllPosts();

    /**
     * Retrieves a post by its id.
     *
     * @param id the id of the post to retrieve
     * @return the post with the given id
     */
    PostDto getPostById(Long id);
}

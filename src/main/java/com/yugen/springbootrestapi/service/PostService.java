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
     * @param pageNo   the page number
     * @param pageSize the page size
     * @return a list of all posts
     */
    List<PostDto> getAllPosts(int pageNo, int pageSize);

    /**
     * Retrieves a post by its id.
     *
     * @param id the id of the post to retrieve
     * @return the post with the given id
     */
    PostDto getPostById(Long id);

    /**
     * Updates a post.
     *
     * @param postDto the data transfer object containing the details of the post
     * @param id      the id of the post to update
     * @return the updated post
     */
    PostDto updatePost(PostDto postDto, Long id);

    /**
     * Deletes a post.
     *
     * @param id the id of the post to delete
     */
    void deletePostById(Long id);

}
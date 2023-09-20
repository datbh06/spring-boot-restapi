package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Post;
import com.yugen.springbootrestapi.payload.PostDto;
import com.yugen.springbootrestapi.repository.PostRepository;
import com.yugen.springbootrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Implementation of the PostService interface.
 */
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    /**
     * Repository for managing posts in the database.
     */
    private PostRepository postRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public PostDto createPost(PostDto postDto) {
        //Convert DTO to Entity
        Post newPost = postRepository.save(mapToEntity(postDto));

        //Convert Entity to DTO
        return mapToDto(newPost);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Converts a Post entity to a PostDto.
     *
     * @param post the post entity to convert
     * @return the converted PostDto
     */
    private PostDto mapToDto(Post post) {
        PostDto postResponse = new PostDto();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setContent(post.getContent());
        return postResponse;
    }

    /**
     * Converts a PostDTO  to a Post Entity.
     *
     * @param postDto the post dto to convert
     * @return the converted post
     */
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}

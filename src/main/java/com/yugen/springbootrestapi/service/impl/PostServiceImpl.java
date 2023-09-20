package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Post;
import com.yugen.springbootrestapi.exception.ResourceNotFoundException;
import com.yugen.springbootrestapi.payload.PostDto;
import com.yugen.springbootrestapi.payload.PostResponse;
import com.yugen.springbootrestapi.repository.PostRepository;
import com.yugen.springbootrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
        //Create Pageable
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Post> posts = postRepository.findAll(pageable);

        //Get content from page object
        List<Post> postList = posts.getContent();

        List<PostDto> content = postList.stream().map(this::mapToDto).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        Post post = postRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Post", "id", id));

        if (postDto.getTitle() != null) {
            post.setTitle(postDto.getTitle());
        }
        if (postDto.getDescription() != null) {
            post.setDescription(postDto.getDescription());
        }
        if (postDto.getContent() != null) {
            post.setContent(postDto.getContent());
        }

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    /**
     * Deletes a post.
     *
     * @param id the id of the post to delete
     */
    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
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

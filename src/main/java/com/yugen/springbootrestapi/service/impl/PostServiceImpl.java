package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Post;
import com.yugen.springbootrestapi.exception.ResourceNotFoundException;
import com.yugen.springbootrestapi.payload.PostDto;
import com.yugen.springbootrestapi.payload.PostResponse;
import com.yugen.springbootrestapi.repository.PostRepository;
import com.yugen.springbootrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public PostDto createPost(PostDto postDto) {
        //Convert DTO to Entity
        Post newPost = postRepository.save(modelMapper.map(postDto, Post.class));

        //Convert Entity to DTO
        return modelMapper.map(newPost, PostDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortOrder) {

        //Create Sort
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        //Create Pageable
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        //Get content from page object
        List<Post> postList = posts.getContent();

        List<PostDto> content = postList.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

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
        return modelMapper.map(post, PostDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

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

        return modelMapper.map(updatedPost, PostDto.class);
    }

    /**
     * Deletes a post.
     *
     * @param id the id of the post to delete
     */
    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

}

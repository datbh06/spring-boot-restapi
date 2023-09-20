package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Post;
import com.yugen.springbootrestapi.payload.PostDto;
import com.yugen.springbootrestapi.repository.PostRepository;
import com.yugen.springbootrestapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        //Convert DTO to Entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        //Convert Entity to DTO
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;

    }
}

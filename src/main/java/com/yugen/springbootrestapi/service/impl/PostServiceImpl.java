package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Post;
import com.yugen.springbootrestapi.payload.PostDto;
import com.yugen.springbootrestapi.repository.PostRepository;
import com.yugen.springbootrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        //Convert DTO to Entity
//        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(mapToEntity(postDto));

        //Convert Entity to DTO
        return mapToDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    //Convert Entity to DTO
    private PostDto mapToDto(Post post) {
        PostDto postResponse = new PostDto();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setContent(post.getContent());
        return postResponse;
    }

    //Convert DTO to Entity
    private Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}

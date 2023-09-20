package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();
}

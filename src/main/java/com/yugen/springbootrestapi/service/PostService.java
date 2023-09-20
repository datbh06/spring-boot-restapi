package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.PostDto;

public interface PostService {

    PostDto createPost(PostDto postDto);
}

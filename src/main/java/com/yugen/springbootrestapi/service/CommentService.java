package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(Long PostId, CommentDto commentDto);
}

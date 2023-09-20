package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Comment;
import com.yugen.springbootrestapi.entity.Post;
import com.yugen.springbootrestapi.exception.ResourceNotFoundException;
import com.yugen.springbootrestapi.payload.CommentDto;
import com.yugen.springbootrestapi.repository.CommentRepository;
import com.yugen.springbootrestapi.repository.PostRepository;
import com.yugen.springbootrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        // Set the post for the comment
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        //Save the comment
        Comment newComment = commentRepository.save(comment);

        // Convert entity to DTO
        return mapToDto(newComment);

    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setMail(comment.getMail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setMail(commentDto.getMail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

}

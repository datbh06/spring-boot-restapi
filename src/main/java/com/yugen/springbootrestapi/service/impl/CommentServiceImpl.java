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

/**
 * Implementation of the CommentService interface.
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    /**
     * Repository for managing comments in the database.
     */
    private CommentRepository commentRepository;

    /**
     * Repository for managing posts in the database.
     */
    private PostRepository postRepository;

    /**
     * Creates a new comment for a specific post.
     *
     * @param postId the ID of the post to which the comment belongs
     * @param commentDto the data transfer object containing the details of the comment
     * @return the created comment
     */
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

    /**
     * Converts a Comment entity to a CommentDto.
     *
     * @param comment the comment entity to convert
     * @return the converted CommentDto
     */
    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setMail(comment.getMail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    /**
     * Converts a CommentDto to a Comment entity.
     *
     * @param commentDto the data transfer object to convert
     * @return the converted Comment entity
     */
    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setMail(commentDto.getMail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}

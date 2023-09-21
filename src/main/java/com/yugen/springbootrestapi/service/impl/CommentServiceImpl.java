package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Comment;
import com.yugen.springbootrestapi.entity.Post;
import com.yugen.springbootrestapi.exception.BlogAPIException;
import com.yugen.springbootrestapi.exception.ResourceNotFoundException;
import com.yugen.springbootrestapi.payload.dto.CommentDto;
import com.yugen.springbootrestapi.repository.CommentRepository;
import com.yugen.springbootrestapi.repository.PostRepository;
import com.yugen.springbootrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
     * ModelMapper for converting entities to DTOs and vice versa.
     */
    private ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
//        Comment comment = mapToEntity(commentDto);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        // Set the post for the comment
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        //Save the comment
        Comment newComment = commentRepository.save(comment);

        // Convert entity to DTO
        return modelMapper.map(newComment, CommentDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        // Check if the post exists
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post", "id", postId);
        }

        // Check if the comment exists
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Check if the comment belongs to the post
        if (!comment.getPost().getId().equals(postId)) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return modelMapper.map(comment, CommentDto.class);
    }

    /**
     * Updates a comment.
     *
     * @param postId     the id of the post to which the comment belongs
     * @param commentId  the id of the comment to update
     * @param commentDto the data transfer object containing the details of the comment
     * @return the updated comment
     */
    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {

        // Check if the post exists
        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post", "id", postId));

        // Check if the comment exists
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Check if the comment belongs to the post
        if (!comment.getPost().getId().equals(postId)) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        // Update the comment
        if (commentDto.getName() != null) {
            comment.setName(commentDto.getName());
        }
        if (commentDto.getMail() != null) {
            comment.setMail(commentDto.getMail());
        }
        if (commentDto.getBody() != null) {
            comment.setBody(commentDto.getBody());
        }

        // Save the updated comment
        Comment updatedComment = commentRepository.save(comment);

        // Convert entity to DTO
        return modelMapper.map(updatedComment, CommentDto.class);
    }

    /**
     * Deletes a comment.
     *
     * @param postId    the id of the post to which the comment belongs
     * @param commentId the id of the comment to delete
     */
    @Override
    public void deleteComment(Long postId, Long commentId) {

        // Check if the post exists
        Post post = postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post", "id", postId));

        // Check if the comment exists
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // Check if the comment belongs to the post
        if (!comment.getPost().getId().equals(postId)) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        // Delete the comment
        commentRepository.delete(comment);
    }

}

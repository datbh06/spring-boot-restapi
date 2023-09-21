package com.yugen.springbootrestapi.repository;

import com.yugen.springbootrestapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing comments in the database.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Finds all comments associated with a specific post.
     *
     * @param postId the ID of the post
     * @return a list of comments associated with the post
     */
    List<Comment> findByPostId(Long postId);
}

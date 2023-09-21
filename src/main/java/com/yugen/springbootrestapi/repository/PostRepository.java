package com.yugen.springbootrestapi.repository;

import com.yugen.springbootrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing users in the database.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * This method is used to retrieve all posts by category ID.
     *
     * @param categoryId The ID of the category.
     * @return A list of all posts by category.
     */
    List<Post> findByCategoryId(Long categoryId);
}

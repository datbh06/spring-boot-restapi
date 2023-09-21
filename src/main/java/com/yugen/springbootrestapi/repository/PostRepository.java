package com.yugen.springbootrestapi.repository;

import com.yugen.springbootrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing users in the database.
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}

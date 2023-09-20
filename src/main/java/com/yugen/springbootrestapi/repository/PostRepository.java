package com.yugen.springbootrestapi.repository;

import com.yugen.springbootrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

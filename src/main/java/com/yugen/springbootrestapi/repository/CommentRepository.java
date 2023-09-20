package com.yugen.springbootrestapi.repository;

import com.yugen.springbootrestapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

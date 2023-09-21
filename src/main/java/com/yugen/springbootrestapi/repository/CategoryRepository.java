package com.yugen.springbootrestapi.repository;

import com.yugen.springbootrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface represents the repository for the Category entity.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package com.yugen.springbootrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Post entity in the database.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

    /**
     * Unique identifier of the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the post. This field is not nullable.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Description of the post. This field is not nullable.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Content of the post. This field is not nullable.
     */
    @Column(name = "content", nullable = false)
    private String content;
}

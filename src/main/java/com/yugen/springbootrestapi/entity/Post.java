package com.yugen.springbootrestapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Post entity in the database.
 */
@Getter
@Setter
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

    /**
     * Set of comments for the post.
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
}

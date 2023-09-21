package com.yugen.springbootrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


/**
 * This class represents an entity for a user.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    /**
     * The ID of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The username of the user.
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * The email of the user.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password of the user
     */
    @Column(nullable = false)
    private String password;

    /**
     * The roles of the user.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}

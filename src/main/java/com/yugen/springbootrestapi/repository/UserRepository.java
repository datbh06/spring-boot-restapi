package com.yugen.springbootrestapi.repository;

import com.yugen.springbootrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing users in the database.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email.
     *
     * @param email the email of the user
     * @return an Optional containing the user if found, or an empty Optional otherwise
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their username or email.
     *
     * @param username the username of the user
     * @param email    the email of the user
     * @return an Optional containing the user if found, or an empty Optional otherwise
     */
    Optional<User> findByUsernameOrEmail(String username, String email);

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user
     * @return an Optional containing the user if found, or an empty Optional otherwise
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user exists by their username.
     *
     * @param username the username of the user
     * @return true if a user with the given username exists, false otherwise
     */
    Boolean existsByUsername(String username);

    /**
     * Checks if a user exists by their email.
     *
     * @param email the email of the user
     * @return true if a user with the given email exists, false otherwise
     */
    Boolean existsByEmail(String email);
}

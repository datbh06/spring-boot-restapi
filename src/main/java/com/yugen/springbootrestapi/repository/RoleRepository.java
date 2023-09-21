package com.yugen.springbootrestapi.repository;

import com.yugen.springbootrestapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing roles in the database.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role
     * @return an Optional containing the role if found, or an empty Optional otherwise
     */
    Optional<Role> findByName(String name);
}

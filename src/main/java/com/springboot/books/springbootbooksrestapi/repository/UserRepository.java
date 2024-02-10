package com.springboot.books.springbootbooksrestapi.repository;

import com.springboot.books.springbootbooksrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//Repository interface for performing CRUD operations on User entities.
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username The username to search for.
     * @return An Optional containing the user if found, or an empty Optional otherwise.
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a user with the specified email exists.
     *
     * @param email The email to check.
     * @return True if a user with the email exists, false otherwise.
     */
    Boolean existsByEmail(String email);

    /**
     * Finds a user by their username or email.
     *
     * @param username The username to search for.
     * @param email    The email to search for.
     * @return An Optional containing the user if found, or an empty Optional otherwise.
     */
    Optional<User> findByUsernameOrEmail(String username, String email);
}
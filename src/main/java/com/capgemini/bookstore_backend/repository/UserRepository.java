package com.capgemini.bookstore_backend.repository;

import com.capgemini.bookstore_backend.model.TheUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity
 * Provides CRUD operations and custom query methods through JpaRepository
 * first param: the Entity/Model class name which is User
 * second param: the @Id that is created inside the Entity/Model class
 */

@Repository // Indicates that this interface is a Spring Data repository
public interface UserRepository extends JpaRepository<TheUser, Long> {
    Optional<TheUser> findByUsername(String username); //gets the user from the DB and returns a TheUser object
}

package com.capgemini.bookstore_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Represents a User entity in the application used for authentication
 * Used lombok annotations to remove boilerplate code
 * Implicitly when we run the application it will create the table with name user
 * with its respective columns name which are the class' attributes
 */
@Getter
@Setter
//! changed the name of the table from the default user to users because of the reserved keyword "user" in JPA which causes ambiguity
@Entity(name = "USERS")
public class TheUser {
    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-increments the primary key value
    private Long id; // Unique identifier for the book

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;
}

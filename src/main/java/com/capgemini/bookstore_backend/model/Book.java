package com.capgemini.bookstore_backend.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a Book entity in the application
 * Used lombok annotations to remove boilerplate code
 * Implicitly when we run the application it will create the table with name book
 * with its respective columns name which are the class' attributes
 */

@Getter
@Setter
@AllArgsConstructor // Generates a constructor with all arguments
@NoArgsConstructor // Generates a no-args constructor
@Entity
public class Book {
    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the primary key value
    private Long bookId; // Unique identifier for the book
    private String title;
    private String author;
    private float price;
}

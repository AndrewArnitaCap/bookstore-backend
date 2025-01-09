package com.capgemini.bookstore_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a Cart entity in the application
 * Implicitly when we run the application it will create the table with name cart
 * with its respective columns name which are the class' attributes
 */

@Getter
@Setter
@AllArgsConstructor // Generates a constructor with all arguments
@NoArgsConstructor // Generates a no-args constructor
@Entity
public class Cart {
    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the primary key value
    private Long cartId; // Unique identifier for the book

    /**
     * to fix this error: Basic collection has element type
     * I added OneToMany relationship between the Cart and Book entities
     * This indicates that a Cart can have many books
     *! it's mapped by the bookId (for now because it needs an intermediary entity aka junction table)
     */

    // cascade ALL means: the same parent operation is auto to its related child entities
    // This means if I did any of the save, update, or delete to the Cart, then the Books related to that Cart will be automatically saved, updated, or deleted as well
    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL)
    private List<Book> books; // list of Books added by the user
    private float subTotal; // sums up the prices of all the books found in the Cart
}

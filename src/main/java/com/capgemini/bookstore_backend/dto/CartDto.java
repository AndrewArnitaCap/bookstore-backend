package com.capgemini.bookstore_backend.dto;

import com.capgemini.bookstore_backend.model.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO used to transfer data between different layers of an application
 * In this between the CartController and CartService
 */

@Getter
@Setter
@AllArgsConstructor // Generates a constructor with all arguments
@NoArgsConstructor // Generates a no-args constructor
public class CartDto {
    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments the primary key value
    private Long cartId; // Unique identifier for the book
    private List<Book> books; // list of Books added by the user
    @PositiveOrZero(message = "Invalid sub total: should be >= 0")
    private float subTotal; // sums up the prices of all the books found in the Cart
}
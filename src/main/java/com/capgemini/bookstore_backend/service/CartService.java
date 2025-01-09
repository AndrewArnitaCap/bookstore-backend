package com.capgemini.bookstore_backend.service;

import com.capgemini.bookstore_backend.dto.BookDto;
import com.capgemini.bookstore_backend.dto.CartDto;

import java.util.List;

/**
 * Service interface for Cart entity
 * Defines methods for CRUD operations and additional business logic
 */

public interface CartService {
    /**
     * Add Book entity to Cart
     * @param bookDto the bookDto to create
     * @return the saved cartDto
     */
    CartDto addBookToCart(BookDto bookDto);

    /**
     * Get all Books that were added to the cart from the DB
     * @return CartDto with all the books
     */
    CartDto getBooksInCart();
}
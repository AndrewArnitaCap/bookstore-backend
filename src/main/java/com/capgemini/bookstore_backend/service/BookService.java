package com.capgemini.bookstore_backend.service;

import com.capgemini.bookstore_backend.dto.BookDto;

import java.util.List;

/**
 * Service interface for Book entity
 * Defines methods for CRUD operations and additional business logic
 */

public interface BookService {
    /**
     * Saves a Book entity
     * @param bookDto the bookDto to create
     * @return the saved bookDto
     */
    BookDto addBook(BookDto bookDto);

    /**
     * Get all Books from the DB
     * @return list of bookDto
     */
    List<BookDto> getAllBooks();
}

package com.capgemini.bookstore_backend.controller;

import com.capgemini.bookstore_backend.dto.BookDto;
import com.capgemini.bookstore_backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Book entities
 * Handles HTTP requests and routes them to the appropriate service methods
 * RestController marks this class as a RESTful controller which combines @Controller and @ResponseBody
 * and automatically and implicitly adds the @ResponseBody annotation to every method
 * RequestMapping maps to your specific API endpoints
 */

@RestController
@RequestMapping("/books") // Base path for the endpoints
public class BookController {
    // Injects the BookService dependency
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService= bookService;
    }

    /**
     * POST operation to create a book and save it in the DB
     * @param bookDto the actual book entity gotten from the request body
     * @return the saved book entity that was added with a 401 CREATED status
     */
    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        BookDto savedBook = bookService.addBook(bookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
}

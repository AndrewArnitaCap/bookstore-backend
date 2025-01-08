package com.capgemini.bookstore_backend.controller;

import com.capgemini.bookstore_backend.dto.BookDto;
import com.capgemini.bookstore_backend.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * By returning a DTO I keep my entity data separate from what I expose through the API
     * @param bookDto the actual book entity gotten from the request body
     * @return the saved book that was added with a 401 CREATED status
     */
    @PostMapping
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto) {
        BookDto newBook = bookService.addBook(bookDto);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    /**
     * GET operation to get all the books found in the DB
     * By returning a List of BookDTO I keep my entity data separate from what I expose through the API
     * @return all books entity that was added with a 200 Success status
     */

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    /**
     * GET operation to get a books based on its ID if found in the DB
     * By returning BookDTO I keep my entity data separate from what I expose through the API
     * @return book that was found with a 200 Success status
     */
    @GetMapping("{bookId}")
    public ResponseEntity<BookDto> findBookById(@PathVariable("bookId") Long bookId) {
        BookDto book = bookService.findBookById(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * DELETE operation to delete a book based on its ID if found in the DB
     * throws an exception if the book to be deleted was not found
     * @return status OK of everything went fine anything
     */
    @DeleteMapping("{bookId}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("bookId") Long bookId) {
        bookService.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT operation to update a book based on its ID if found in the DB
     * same as above, so by returning BookDTO I keep my entity data separate from what I expose through the API
     * @return book that was found with a 200 Success status
     */
    @PutMapping("{bookId}")
    public ResponseEntity<BookDto> updateBookById(@RequestBody BookDto bookDto, @PathVariable("bookId") Long bookId) {
        BookDto updatedBook = bookService.updateBookById(bookDto, bookId);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
}

package com.capgemini.bookstore_backend.service;

import com.capgemini.bookstore_backend.Mapper.BookMapper;
import com.capgemini.bookstore_backend.dto.BookDto;
import com.capgemini.bookstore_backend.model.Book;
import com.capgemini.bookstore_backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Actual implementation of BookService
 * Provide the business logic for handling book related operations
 */
@Service // Marks this class as a Spring service component
public class BookServiceImpl implements BookService {
    /**
     * final keyword makes sure that the dependency is immutable
     * and that bookRepository is never going to be changed
     */
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Creates the book entity in the db
     * This is used in the PostMapping operation in the BookController
     * @param bookDto the book to create
     * @return book entity if successful
     * or throws an error if something fails
     */
    @Override
    public BookDto addBook(BookDto bookDto) {
        try {
            // Convert DTO to entity
            Book book = BookMapper.INSTANCE.mapBookDtoToBook(bookDto);
            // Save the entity
            Book savedBook = bookRepository.save(book);
            // Convert the saved entity back to DTO
            return BookMapper.INSTANCE.mapBookToBookDto(savedBook);
        } catch (Exception e) {
            // Handle the exception
            throw new RuntimeException("Error creating book", e);
        }
    }
}

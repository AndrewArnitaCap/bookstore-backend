package com.capgemini.bookstore_backend.dto;

import lombok.*;

/**
 * DTO used to transfer data between different layers of an application
 * In this between the BookController and BookService
 * Without DTOs I might expose the whole entity class
 */

@Getter
@Setter
@AllArgsConstructor // Generates a constructor with all arguments
@NoArgsConstructor // Generates a no-args constructor
public class BookDto {
    private Long bookId;
    private String title;
    private String author;
    private float price;
}

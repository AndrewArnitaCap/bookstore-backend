package com.capgemini.bookstore_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * DTO used to transfer data between different layers of an application
 * In this between the BookController and BookService
 * Without DTOs I might expose the whole entity class
 * Validating data using spring boot data validation for null, empty, and negative/zero values
 */

@Getter
@Setter
@AllArgsConstructor // Generates a constructor with all arguments
@NoArgsConstructor // Generates a no-args constructor
public class BookDto {
    private Long bookId;

    @NotBlank(message = "Invalid title: title should not be empty")
    @NotNull(message = "Invalid title: title must not be null")
    private String title;

    @NotBlank(message = "Invalid author: author should be empty")
    @NotNull(message = "Invalid author: author must not be null")
    private String author;

    @Min(value = 1, message = "Invalid price: should be >= 1")
    @NotNull(message = "Invalid price: price must not be null")
    private float price;
}

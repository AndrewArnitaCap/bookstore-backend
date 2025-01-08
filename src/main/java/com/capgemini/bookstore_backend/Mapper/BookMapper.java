package com.capgemini.bookstore_backend.Mapper;

import com.capgemini.bookstore_backend.dto.BookDto;
import com.capgemini.bookstore_backend.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper is from MapStruct
 * This will map between entities BookDTO to Book entity
 * It simplifies and automates the mapping process
 */

@Mapper(componentModel = "spring")
public interface BookMapper {
    /**
     * call to the getMapper method of the Mappers class
     * it's a method call that returns an instance of the BookMapper interface
     */
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    /**
     * maps Book entity to BookDto
     * @param book
     * @return BookDto
     */
    BookDto mapBookToBookDto(Book book);

    /**
     * maps BookDto to Book entity
     * @param bookDto
     * @return Book entity
     */
    Book mapBookDtoToBook(BookDto bookDto);
}

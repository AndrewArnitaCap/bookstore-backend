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
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto mapBookToBookDto(Book book);
    Book mapBookDtoToBook(BookDto bookDto);
}

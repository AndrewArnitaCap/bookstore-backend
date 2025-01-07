package com.capgemini.bookstore_backend.repository;

import com.capgemini.bookstore_backend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Book entity
 * Provides CRUD operations and custom query methods through JpaRepository
 * first param: the Entity/Model class name
 * second param: the @Id that is created inside the Entity/Model class
 */

@Repository // Indicates that this interface is a Spring Data repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

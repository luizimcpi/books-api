package com.devlhse.booksapi.service;

import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.entity.Book;

import java.util.Optional;

public interface BookService {


    /**
     * Create a book in the database
     *
     * @param book
     * @return Book
     */
    Book create(Book book);

    /**
     * Convert DTO to Book Entity.
     *
     * @param bookDto
     * @return Book
     */
    Book dtoToEntityConverter(BookDto bookDto);

    /**
     * Convert entity to Book Dto.
     *
     * @param book
     * @return bookDto
     */
    BookDto entityToDtoConverter(Book book);

    /**
     * Find a book by ID.
     *
     * @param id
     * @return Optional<Book>
     */
    Optional<Book> findById(Long id);

    /**
     * Validate fields.
     *
     * @param bookDto
     */
    void validateFields(BookDto bookDto);

}

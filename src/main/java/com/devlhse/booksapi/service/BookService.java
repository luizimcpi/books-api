package com.devlhse.booksapi.service;

import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.entity.Book;

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

}

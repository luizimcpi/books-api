package com.devlhse.booksapi.component;

import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookConversorComponent {

    public Book dtoToEntityConverter(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setISBN(bookDto.getISBN());
        book.setLanguage(bookDto.getLanguage());
        return book;
    }

    public BookDto entityToDtoConverter(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setISBN(book.getISBN());
        bookDto.setLanguage(book.getLanguage());
        return bookDto;
    }
}

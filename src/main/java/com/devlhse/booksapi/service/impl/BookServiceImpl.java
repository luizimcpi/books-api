package com.devlhse.booksapi.service.impl;

import com.devlhse.booksapi.component.BookConversorComponent;
import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.entity.Book;
import com.devlhse.booksapi.repository.BookRepository;
import com.devlhse.booksapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookConversorComponent bookConversorComponent;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository, final BookConversorComponent bookConversorComponent) {
        this.bookRepository = bookRepository;
        this.bookConversorComponent = bookConversorComponent;
    }

    @Override
    public Book create(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public Book dtoToEntityConverter(BookDto bookDto) {
        return bookConversorComponent.dtoToEntityConverter(bookDto);
    }

    @Override
    public BookDto entityToDtoConverter(Book book) {
        return bookConversorComponent.entityToDtoConverter(book);
    }
}

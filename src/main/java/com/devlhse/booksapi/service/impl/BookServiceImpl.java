package com.devlhse.booksapi.service.impl;

import com.devlhse.booksapi.component.BookConversorComponent;
import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.dto.BookListResponseDto;
import com.devlhse.booksapi.entity.Book;
import com.devlhse.booksapi.exception.FieldEmptyException;
import com.devlhse.booksapi.repository.BookRepository;
import com.devlhse.booksapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void validateFields(BookDto bookDto) {
        if(bookDto.getTitle() == null || bookDto.getTitle().isEmpty()){ throw new FieldEmptyException("title can not be empty");}
        if(bookDto.getDescription() == null || bookDto.getDescription().isEmpty()){ throw new FieldEmptyException("description can not be empty");}
        if(bookDto.getIsbn() == null || bookDto.getIsbn().isEmpty()){ throw new FieldEmptyException("ISBN can not be empty");}
        if(bookDto.getLanguage() == null || bookDto.getLanguage().isEmpty()){ throw new FieldEmptyException("language can not be empty");}
    }

    @Override
    public BookListResponseDto getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> booksResponse = new ArrayList<>();
        if(books != null && !books.isEmpty()){
            for (Book book : books) {
                booksResponse.add(this.entityToDtoConverter(book));
            }
        }

        BookListResponseDto bookListResponseDto = new BookListResponseDto();
        bookListResponseDto.setNumberBooks(booksResponse.size());
        bookListResponseDto.setBooks(booksResponse);

        return bookListResponseDto;
    }
}

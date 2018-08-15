package com.devlhse.booksapi.controller;

import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.dto.BookListResponseDto;
import com.devlhse.booksapi.entity.Book;
import com.devlhse.booksapi.exception.EntityNotFoundException;
import com.devlhse.booksapi.exception.FieldEmptyException;
import com.devlhse.booksapi.service.BookService;
import com.devlhse.booksapi.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookServiceImpl bookService) {
            this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> create(@Valid @RequestBody BookDto bookDto, BindingResult result) throws URISyntaxException, FieldEmptyException {
        bookService.validateFields(bookDto);
        Book book = bookService.dtoToEntityConverter(bookDto);
        this.bookService.create(book);
        return ResponseEntity.created(new URI("/books")).body(bookService.entityToDtoConverter(book));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> findBookById(@PathVariable("id") Long id) throws EntityNotFoundException {
        Optional<Book> book = this.bookService.findById(id);

        if (!book.isPresent()) {
            throw new EntityNotFoundException("Book can not found by id.");
        }

        return ResponseEntity.ok(bookService.entityToDtoConverter(book.get()));
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookListResponseDto> findAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}

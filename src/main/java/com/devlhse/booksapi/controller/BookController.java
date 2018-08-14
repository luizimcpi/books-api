package com.devlhse.booksapi.controller;

import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.dto.Response;
import com.devlhse.booksapi.entity.Book;
import com.devlhse.booksapi.service.BookService;
import com.devlhse.booksapi.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/books")
public class BookController {


    private final BookService bookService;

    @Autowired
    public BookController(final BookServiceImpl bookService) {
            this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<BookDto>> create(@Valid @RequestBody BookDto bookDto, BindingResult result) throws URISyntaxException {
        Response<BookDto> response = new Response<>();
        Book book = bookService.dtoToEntityConverter(bookDto);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        this.bookService.create(book);

        response.setData(bookService.entityToDtoConverter(book));
        return ResponseEntity.created(new URI("/books")).body(response);
    }
}

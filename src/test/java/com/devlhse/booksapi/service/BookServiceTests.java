package com.devlhse.booksapi.service;


import com.devlhse.booksapi.entity.Book;
import com.devlhse.booksapi.repository.BookRepository;
import com.devlhse.booksapi.service.impl.BookServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTests {

    private static final Long VALID_BOOK_ID = 1L;
    private static final String VALID_BOOK_TITLE = "Book Title Example";
    private static final String VALID_BOOK_DESCRIPTION = "Book description example";
    private static final String VALID_BOOK_ISBN = "9781617293290";
    private static final String VALID_BOOK_LANGUAGE = "BR";


    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;


    @Test
    public void shouldCreateBookWithSuccessWhenAllFieldsAreNotEmpty(){
        Book book = getBookEntity();
        when(bookRepository.save(book)).thenReturn(book);
        Assert.assertNotNull(bookService.create(book));
    }

    @Test
    public void shouldFindBookByIdWithSuccessWhenIdExists(){
        Optional<Book> book = Optional.ofNullable(getBookEntity());
        when(bookRepository.findById(VALID_BOOK_ID)).thenReturn(book);
        Assert.assertNotNull(bookService.findById(VALID_BOOK_ID));
    }

    private Book getBookEntity() {
        Book book = new Book();
        book.setId(VALID_BOOK_ID);
        book.setTitle(VALID_BOOK_TITLE);
        book.setDescription(VALID_BOOK_DESCRIPTION);
        book.setIsbn(VALID_BOOK_ISBN);
        book.setLanguage(VALID_BOOK_LANGUAGE);
        return book;
    }
}

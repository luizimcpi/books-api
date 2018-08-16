package com.devlhse.booksapi.component;

import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.entity.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookConversorComponentTests {

    private static final Long VALID_BOOK_ID = 1L;
    private static final String VALID_BOOK_TITLE = "Book Title Example";
    private static final String VALID_BOOK_DESCRIPTION = "Book description example";
    private static final String VALID_BOOK_ISBN = "9781617293290";
    private static final String VALID_BOOK_LANGUAGE = "BR";

    @InjectMocks
    private BookConversorComponent bookConversorComponent;

    @Test
    public void shouldConvertBookDtoWithSuccess() {
        Assert.assertNotNull(bookConversorComponent.dtoToEntityConverter(getBookDto()));
    }

    @Test
    public void shouldConvertEntityBookWithSuccess() {
        Assert.assertNotNull(bookConversorComponent.entityToDtoConverter(getBookEntity()));
    }

    private BookDto getBookDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(VALID_BOOK_ID);
        bookDto.setTitle(VALID_BOOK_TITLE);
        bookDto.setDescription(VALID_BOOK_DESCRIPTION);
        bookDto.setIsbn(VALID_BOOK_ISBN);
        bookDto.setLanguage(VALID_BOOK_LANGUAGE);
        return bookDto;
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

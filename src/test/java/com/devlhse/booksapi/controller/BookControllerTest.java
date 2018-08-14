package com.devlhse.booksapi.controller;

import com.devlhse.booksapi.dto.BookDto;
import com.devlhse.booksapi.entity.Book;
import com.devlhse.booksapi.service.impl.BookServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerTest {

    private static final String BASE_URL = "/books";
    private static final Long VALID_BOOK_ID = 1L;
    private static final String VALID_BOOK_TITLE = "Book Title Example";
    private static final String VALID_BOOK_DESCRIPTION = "Book description example";
    private static final String VALID_BOOK_ISBN = "9781617293290";
    private static final String VALID_BOOK_LANGUAGE = "BR";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookServiceImpl bookService;


    @Test
    public void testCreateBookWithSuccess() throws Exception {
        Book book = getBookEntity();
        BookDto bookDto = getReturnedDto();
        BDDMockito.given(this.bookService.dtoToEntityConverter(Mockito.any(BookDto.class))).willReturn(book);
        BDDMockito.given(this.bookService.create(Mockito.any(Book.class))).willReturn(book);
        BDDMockito.given(bookService.entityToDtoConverter(Mockito.any(Book.class))).willReturn(bookDto);

        mvc.perform(MockMvcRequestBuilders.post(BASE_URL).content(this.getValidJsonPostRequest())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(VALID_BOOK_ID))
                .andExpect(jsonPath("$.data.title").value(VALID_BOOK_TITLE))
                .andExpect(jsonPath("$.data.description").value(VALID_BOOK_DESCRIPTION))
                .andExpect(jsonPath("$.data.isbn").value(VALID_BOOK_ISBN))
                .andExpect(jsonPath("$.data.language").value(VALID_BOOK_LANGUAGE))
                .andExpect(jsonPath("$.errors").isEmpty());
    }

    private BookDto getReturnedDto() {
        BookDto bookDto = new BookDto();
        bookDto.setId(VALID_BOOK_ID);
        bookDto.setTitle(VALID_BOOK_TITLE);
        bookDto.setDescription(VALID_BOOK_DESCRIPTION);
        bookDto.setISBN(VALID_BOOK_ISBN);
        bookDto.setLanguage(VALID_BOOK_LANGUAGE);
        return bookDto;
    }

    private Book getBookEntity() {
        Book book = new Book();
        book.setId(VALID_BOOK_ID);
        book.setTitle(VALID_BOOK_TITLE);
        book.setDescription(VALID_BOOK_DESCRIPTION);
        book.setISBN(VALID_BOOK_ISBN);
        book.setLanguage(VALID_BOOK_LANGUAGE);
        return book;
    }

    private String getValidJsonPostRequest() throws JsonProcessingException {
        BookDto bookDto = new BookDto();
        bookDto.setId(null);
        bookDto.setTitle(VALID_BOOK_TITLE);
        bookDto.setDescription(VALID_BOOK_DESCRIPTION);
        bookDto.setISBN(VALID_BOOK_ISBN);
        bookDto.setLanguage(VALID_BOOK_LANGUAGE);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(bookDto);
    }
}

package com.devlhse.booksapi.component;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class BookListComponentTests {

    @InjectMocks
    private BookListComponent bookListComponent;


    @Test
    public void shouldContainsTwentyFourBooks() throws IOException {
        Assert.assertEquals(bookListComponent.getBooksFromUrl(0L).size(), 24);
    }
}

package com.devlhse.booksapi.component;

import com.devlhse.booksapi.dto.BookDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookListComponent {

    public static List<BookDto> getBooksFromUrl() throws IOException {
        List<BookDto> books = new ArrayList<>();
        Document doc = Jsoup.connect("https://kotlinlang.org/docs/books.html").get();
        Elements title = doc.select("h2");
        BookDto bookDto;
        for (Element h2 : title){
            bookDto = new BookDto();
            bookDto.setTitle(h2.text());
            books.add(bookDto);
        }

        return books;
    }

    public static void main(String[] args) throws IOException {
        getBooksFromUrl();
    }

}

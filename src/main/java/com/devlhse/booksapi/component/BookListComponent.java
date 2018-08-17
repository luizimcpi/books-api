package com.devlhse.booksapi.component;

import com.devlhse.booksapi.entity.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookListComponent {

    private static final String TAG_PARAGRAPH = "p";
    private static final String WHITE_SPACE = " ";
    private static final String KOTLIN_BOOKS_URL = "https://kotlinlang.org/docs/books.html";
    private static final Logger log = LoggerFactory.getLogger(BookListComponent.class);

    public List<Book> getBooksFromUrl(Long lastId) throws IOException {
        log.info("Iniciando busca de livros no site...");
        List<Book> books = new ArrayList<>();
        Book book = null;
        StringBuilder description = null;
        Document mainDoc = Jsoup.connect(KOTLIN_BOOKS_URL).get();
		Elements titles = mainDoc.select("h2");

		for (Element h2 : titles){
		    book = new Book();
		    description = new StringBuilder();
            book.setId(++lastId);
            book.setTitle(h2.text());
			getNextElements(h2, description, book);
            book.setDescription(description.toString());
            log.info("Livro: "+ book.getId() +" e suas informações foram preenchidas...");
			books.add(book);
		}

        return books;
    }


    private void getNextElements(Element h2, StringBuilder description, Book book) throws IOException {
        Element mainNode = h2.nextElementSibling();
        if (mainNode != null && TAG_PARAGRAPH.equalsIgnoreCase(mainNode.nodeName())) {
            description.append(mainNode.text()).append(System.lineSeparator());
            getNextElements(mainNode, description, book);
        }else if(mainNode != null && mainNode.nodeName() != "h2"){
            if(mainNode.getElementsByClass("event-lang").text() != null && !mainNode.getElementsByClass("event-lang").text().isEmpty()){
                book.setLanguage(mainNode.getElementsByClass("event-lang").text());
            }
            if(mainNode.select("a").first() != null ){
                Element link = mainNode.select("a").first();
                String absHref = link.attr("abs:href");
                book.setIsbn(getBookIsbn(absHref));
            }
            getNextElements(mainNode, description, book);
        }
    }


    private static String getBookIsbn(String absHref) {
        String isbn = "Unavailable";
        try {
            URL url = new URL(absHref);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("ISBN")) {
                    isbn = formatIsbnNumber(inputLine);
                    break;
                }
            }
            in.close();
            return isbn;
        } catch (Exception e) {
            return isbn;
        }

    }

    public static String formatIsbnNumber(String html) {
        String isbn = Jsoup.parse(html).text();
        String[] isbnArray = isbn.split(WHITE_SPACE);
        return isbnArray[1];
    }

}


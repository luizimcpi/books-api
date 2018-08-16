package com.devlhse.booksapi.component;

import com.devlhse.booksapi.dto.BookDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class BookListComponent {

    private static final String TAG_PARAGRAPH = "p";

    public List<BookDto> getBooksFromUrl(Long lastId) throws IOException {
        List<BookDto> books = new ArrayList<>();
        BookDto bookDto = null;
        StringBuilder description = null;
        Document mainDoc = Jsoup.connect("https://kotlinlang.org/docs/books.html").get();
		Elements titles = mainDoc.select("h2");

		for (Element h2 : titles){
		    bookDto = new BookDto();
		    description = new StringBuilder();
		    bookDto.setId(++lastId);
			bookDto.setTitle(h2.text());
			getNextElements(h2, description, bookDto);
			bookDto.setDescription(description.toString());
            System.out.println("BookDTO >>>> " + bookDto.toString());
			books.add(bookDto);
		}

        return books;
    }


    private void getNextElements(Element h2, StringBuilder description, BookDto bookDto) throws IOException {
        Element mainNode = h2.nextElementSibling();
        if (mainNode != null && TAG_PARAGRAPH.equalsIgnoreCase(mainNode.nodeName())) {
            description.append(mainNode.text()).append("\n");
            getNextElements(mainNode, description, bookDto);
        }else if(mainNode != null && mainNode.nodeName() != "h2"){
            if(mainNode.getElementsByClass("event-lang").text() != null && !mainNode.getElementsByClass("event-lang").text().isEmpty()){
                bookDto.setLanguage(mainNode.getElementsByClass("event-lang").text());
            }
            if(mainNode.select("a").first() != null ){
                Element link = mainNode.select("a").first();
                String absHref = link.attr("abs:href");
                bookDto.setIsbn(getBookIsbn(absHref));
            }
            getNextElements(mainNode, description, bookDto);
        }
    }


    private static String getBookIsbn(String absHref) {
        String isbn = "Unavailable";
        try {
//            Document bookInfosDoc = Jsoup.connect(absHref).get();

            URL oracle = new URL(absHref);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("ISBN")) {
                    isbn = inputLine;
//                    System.out.println("linha com ISBN >>>>> " + inputLine);
                    break;
                }
            }
            in.close();
            return isbn;
        } catch (Exception e) {
            return isbn;
        }

    }

}


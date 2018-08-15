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

public static final String TAG_PARAGRAPH = "p";
//
//	public static void main(String[] args) throws IOException {
//		Document mainDoc = Jsoup.connect("https://kotlinlang.org/docs/books.html").get();
//		Elements titles = mainDoc.select("h2");
//
//		for (Element h2 : titles){
//			StringBuilder description = new StringBuilder();
//			System.out.println("==========================================");
//			System.out.println("Titulo: " + h2.text());
//			getNextElements(h2, description);
//			System.out.println("Description: " + description.toString());
//		}
//
//	}
//
//
//	private static void getNextElements(Element h2, StringBuilder description) throws IOException {
//		Element mainNode = h2.nextElementSibling();
//		if (mainNode != null && TAG_PARAGRAPH.equalsIgnoreCase(mainNode.nodeName())) {
//			description.append(mainNode.text()).append("\n");
//			getNextElements(mainNode, description);
//		}else if(mainNode != null && mainNode.nodeName() != "h2"){
//			if(mainNode.getElementsByClass("event-lang").text() != null && !mainNode.getElementsByClass("event-lang").text().isEmpty()){
//				System.out.println("LANGUAGE: " + mainNode.getElementsByClass("event-lang").text());
//			}
//			if(mainNode.select("a").first() != null ){
//				Element link = mainNode.select("a").first();
//				String absHref = link.attr("abs:href");
//				System.out.println("LINK:>>>>>>> " + absHref);
//				String isbn = getBookIsbn(absHref);
//				System.out.println("ISBN: " + isbn);
//			}
//			getNextElements(mainNode, description);
//		}
//	}
//
//	private static String getBookIsbn(String absHref) {
//		try {
//			Document bookInfosDoc = Jsoup.connect(absHref).get();
//			return "Unavailable";
//		} catch (Exception e){
//			return "Unavailable Exception";
//		}
//	}

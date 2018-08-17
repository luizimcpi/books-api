package com.devlhse.booksapi.component;

import com.devlhse.booksapi.entity.Book;
import com.devlhse.booksapi.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksDataLoader implements ApplicationRunner {

    private final BookListComponent bookListComponent;
    private final BookRepository bookRepository;
    private static final Logger log = LoggerFactory.getLogger(BooksDataLoader.class);

    @Autowired
    public BooksDataLoader(final BookRepository bookRepository, final BookListComponent bookListComponent) {
        this.bookRepository = bookRepository;
        this.bookListComponent = bookListComponent;
    }

    public void run(ApplicationArguments args) {
        Long lastId = 0L;
        List<Book> books = bookRepository.findAll();
        if(books != null && !books.isEmpty()){
            lastId = books.get(books.size()-1).getId();
        }
        try {
            List<Book> booksFromUrl = bookListComponent.getBooksFromUrl(lastId);
            if(booksFromUrl != null && !booksFromUrl.isEmpty()){
                for (Book newBook : booksFromUrl) {
                    log.info("Aguarde só mais um pouco estamos carregando o Livro: "+ newBook.getId() + " na base de dados...");
                    bookRepository.save(newBook);
                }
            }
            log.info("API pronta para ser utilizada. Obrigado por aguardar !!!");
        }catch (Exception e){
            log.error("Sorry! Something wrong occured at BooksDataLoader.run.getBooksFromUrl");
        }

    }
}

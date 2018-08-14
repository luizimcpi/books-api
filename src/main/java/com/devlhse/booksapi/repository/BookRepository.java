package com.devlhse.booksapi.repository;

import com.devlhse.booksapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

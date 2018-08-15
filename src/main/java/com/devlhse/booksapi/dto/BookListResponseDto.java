package com.devlhse.booksapi.dto;

import java.util.List;

public class BookListResponseDto {


    private int numberBooks;
    private List<BookDto> books;

    public BookListResponseDto() {
    }

    public int getNumberBooks() {
        return numberBooks;
    }

    public void setNumberBooks(int numberBooks) {
        this.numberBooks = numberBooks;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookListResponseDto{" +
                "numberBooks=" + numberBooks +
                ", books=" + books +
                '}';
    }

}

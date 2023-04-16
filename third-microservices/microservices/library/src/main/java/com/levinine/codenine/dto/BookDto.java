package com.levinine.codenine.dto;

import com.levinine.codenine.library.entities.Book;

public record BookDto(Long id, String name, String author, String description) {

    public static BookDto fromModel(Book book) {
        return new BookDto(
            book.getId(),
            book.getName(),
            book.getAuthor(),
            book.getDescription()
        );
    }
}

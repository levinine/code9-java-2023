package com.levinine.codenine.gateway.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.levinine.codenine.gateway.books.BookClient;
import com.levinine.codenine.gateway.books.BookDto;
import com.levinine.codenine.gateway.dtos.GreetingDto;

@Component
public class BookServiceImpl implements BookService {

    private BookClient bookClient;

    @Autowired
    public BookServiceImpl(BookClient bookClient) {
        this.bookClient = bookClient;
    }

    @Override
    public GreetingDto hello(String name) {
        return GreetingDto.fromHelloDto(bookClient.hello(name));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookClient.getAllBooks();
    }

}

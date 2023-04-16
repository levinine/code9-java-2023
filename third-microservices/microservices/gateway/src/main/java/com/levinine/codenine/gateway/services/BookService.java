package com.levinine.codenine.gateway.services;

import java.util.List;

import com.levinine.codenine.gateway.books.BookDto;
import com.levinine.codenine.gateway.dtos.GreetingDto;

public interface BookService {

    public GreetingDto hello(String name);

    public List<BookDto> getAllBooks();
    
}
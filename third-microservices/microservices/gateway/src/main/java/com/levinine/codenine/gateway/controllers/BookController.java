package com.levinine.codenine.gateway.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levinine.codenine.gateway.books.BookDto;
import com.levinine.codenine.gateway.dtos.GreetingDto;
import com.levinine.codenine.gateway.services.BookService;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/hello/{name}")
    public GreetingDto hello(@PathVariable(name = "name") String name) {
        return bookService.hello(name);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

}

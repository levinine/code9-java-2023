package com.levinine.codenine.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levinine.codenine.dto.BookDto;
import com.levinine.codenine.dto.HelloDto;
import com.levinine.codenine.library.services.BookService;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/hello/{name}")
    public HelloDto hello(@PathVariable("name") String name) {
        return new HelloDto(name);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return this.bookService.getAll();
    }
}

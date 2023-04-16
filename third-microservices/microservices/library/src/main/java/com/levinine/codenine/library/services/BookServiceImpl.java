package com.levinine.codenine.library.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.levinine.codenine.dto.BookDto;
import com.levinine.codenine.repositories.BookRepository;

@Component
public class BookServiceImpl implements BookService {
    
    private BookRepository bookRepository;
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll()
            .stream()
            .map(BookDto::fromModel)
            .collect(Collectors.toList());
        //return new ArrayList<>();
    }

}

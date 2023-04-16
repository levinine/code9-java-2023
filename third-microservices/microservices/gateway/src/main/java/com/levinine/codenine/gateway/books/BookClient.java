package com.levinine.codenine.gateway.books;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "library-service")
public interface BookClient {

    @GetMapping("/v1/books/hello/{name}")
    public HelloDto hello(@PathVariable(name = "name") String name);

    @GetMapping("/v1/books")
    public List<BookDto> getAllBooks();

}

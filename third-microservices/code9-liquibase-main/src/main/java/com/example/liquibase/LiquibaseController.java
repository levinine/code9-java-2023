package com.example.liquibase;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiquibaseController {

    @RequestMapping("/")
    public String Hello() {
        return "Hello world!";
    }
}

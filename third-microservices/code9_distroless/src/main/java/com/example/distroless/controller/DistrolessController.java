package com.example.distroless.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrolessController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

}

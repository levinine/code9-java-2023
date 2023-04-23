package com.code9.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    @GetMapping("/secured/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint.");
    }

    @GetMapping("/hello-everyone")
    public ResponseEntity<String> sayHelloToEveryone() {
        return ResponseEntity.ok("Hello from open endpoint.");
    }
}

package com.levinine.codenine.monolith.controllers;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levinine.codenine.monolith.controllers.dtos.GreetDTO;
import com.levinine.codenine.monolith.services.GreeterService;

@RestController
@RequestMapping("/v1/hello")
public class HelloController {

    private GreeterService greeterService;

    @Inject
    public HelloController(@Qualifier("GreeterImplementation") GreeterService greeterService) {
        this.greeterService = greeterService;
    }

    @GetMapping
    public GreetDTO helloWorld() {
        return new GreetDTO(greeterService.defaultGreeting());
    }


    @GetMapping(path = "/{name}")
    public GreetDTO hello(@PathVariable("name") String name) {
        return new GreetDTO(greeterService.greet(name));
    }
}
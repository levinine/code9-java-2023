package com.levinine.codenine.monolith.services;

public interface GreeterService {
    
    default String defaultGreeting() {
        return "Hello world";
    }

    String greet(String name);
}

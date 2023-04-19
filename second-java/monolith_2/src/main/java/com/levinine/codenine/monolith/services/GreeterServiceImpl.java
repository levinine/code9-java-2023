package com.levinine.codenine.monolith.services;

import org.springframework.stereotype.Component;

@Component("GreeterImplementation")
public class GreeterServiceImpl implements GreeterService {

    @Override
    public String greet(String name) {
        return name + "says hello to you too";
    }
}

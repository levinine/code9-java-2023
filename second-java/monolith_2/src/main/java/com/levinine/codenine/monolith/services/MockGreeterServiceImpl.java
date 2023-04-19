package com.levinine.codenine.monolith.services;

import org.springframework.stereotype.Component;

@Component("GreeterMockImplementation")
public class MockGreeterServiceImpl implements GreeterService {
    @Override
    public String greet(String name) {
        return "You should not see this";
    }
}
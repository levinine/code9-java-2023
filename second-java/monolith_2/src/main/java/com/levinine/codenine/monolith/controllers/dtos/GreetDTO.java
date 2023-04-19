package com.levinine.codenine.monolith.controllers.dtos;

import java.util.Objects;

public record GreetDTO(String text) {

    public GreetDTO {
        Objects.requireNonNull(text);
    }
}

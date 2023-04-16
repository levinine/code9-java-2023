package com.levinine.codenine.gateway.dtos;

import org.apache.commons.lang.StringUtils;

import com.levinine.codenine.gateway.books.HelloDto;

public record GreetingDto(String greeting) {

    public static GreetingDto fromHelloDto(HelloDto dto) {
        return new GreetingDto(
            StringUtils.isBlank(dto.name()) ? 
                "Hello to you too dear user" :
                "Hello to you too dear " + dto.name()
        );
    }

}
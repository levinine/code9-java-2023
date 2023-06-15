package com.levi9.code9.product.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductDto {

    Long id;
    String name;
    double price;
    int quantity;
}

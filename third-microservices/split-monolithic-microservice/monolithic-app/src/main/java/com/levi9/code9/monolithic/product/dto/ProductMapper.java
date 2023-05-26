package com.levi9.code9.monolithic.product.dto;

import com.levi9.code9.monolithic.product.Product;
import com.levi9.code9.monolithic.util.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    @Override
    public Product dtoToEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .id(dto.getId())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }

    @Override
    public ProductDto entityToDto(Product entity) {
        return ProductDto.builder()
                .name(entity.getName())
                .id(entity.getId())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .build();
    }
}

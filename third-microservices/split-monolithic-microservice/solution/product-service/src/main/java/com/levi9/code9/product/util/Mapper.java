package com.levi9.code9.product.util;

public interface Mapper<E, D> {

    E dtoToEntity(D dto);

    D entityToDto(E entity);
}

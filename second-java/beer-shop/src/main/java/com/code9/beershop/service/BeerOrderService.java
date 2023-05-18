package com.code9.beershop.service;

import com.code9.beershop.model.BeerOrder;

import java.util.List;

public interface BeerOrderService {
    BeerOrder orderBeer(BeerOrder beerOrder);

    List<BeerOrder> findByZip(String zip);
}

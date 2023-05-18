package com.code9.beershop.service;

import com.code9.beershop.model.Beer;

import java.util.List;

public interface BeerService {

    List<Beer> getAllBeers();

    Beer getBeerById(Long id);

    Beer saveBeer(Beer beer);

}

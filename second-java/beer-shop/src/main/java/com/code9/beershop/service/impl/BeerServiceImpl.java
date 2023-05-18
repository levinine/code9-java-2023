package com.code9.beershop.service.impl;

import com.code9.beershop.model.Beer;
import com.code9.beershop.repository.BeerRepository;
import com.code9.beershop.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the BeerService interface.
 */
@Service
public class BeerServiceImpl implements BeerService {

    @Autowired
    private BeerRepository beerRepository;

    /**
     * Retrieves all beers.
     *
     * @return The list of all beers.
     */
    @Override
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    /**
     * Retrieves a beer by its ID.
     *
     * @param id The ID of the beer to retrieve.
     * @return The beer with the specified ID.
     */
    @Override
    public Beer getBeerById(Long id) {
        return beerRepository.findById(id).get();
    }

    /**
     * Saves a new beer.
     *
     * @param beer The BeerDto object containing the beer details.
     * @return The saved beer object.
     */
    @Override
    public Beer saveBeer(Beer beer) {
        beer.setCreatedAt(LocalDateTime.now());
        return beerRepository.save(beer);
    }
}

package com.code9.beershop.service.impl;

import com.code9.beershop.model.BeerOrder;
import com.code9.beershop.repository.BeerOrderRepository;
import com.code9.beershop.repository.BeerRepository;
import com.code9.beershop.service.BeerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the BeerOrderService interface.
 */
@Service
public class BeerOrderServiceImpl implements BeerOrderService {

    @Autowired
    private BeerOrderRepository beerOrderRepository;

    @Autowired
    private BeerRepository beerService;

    /**
     * Places an order for beer.
     *
     * @param beerOrder The BeerOrderDto object containing the beer order details.
     * @return The saved BeerOrder object.
     */
    @Override
    public BeerOrder orderBeer(BeerOrder beerOrder) {
        beerOrder.setPlacedAt(LocalDateTime.now());
        return beerOrderRepository.save(beerOrder);
    }

    @Override
    public List<BeerOrder> findByZip(String zip) {
        return beerOrderRepository.findByZip(zip);
    }
}

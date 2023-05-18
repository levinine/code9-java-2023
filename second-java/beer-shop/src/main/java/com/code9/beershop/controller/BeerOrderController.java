package com.code9.beershop.controller;

import com.code9.beershop.convertor.BeerOrderConverter;
import com.code9.beershop.dto.BeerOrderDto;
import com.code9.beershop.model.BeerOrder;
import com.code9.beershop.service.BeerOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Validated
public class BeerOrderController {

    @Autowired
    private BeerOrderService beerOrderService;

    @Autowired
    private BeerOrderConverter beerOrderConverter;

    /**
     * Places an order for beer.
     *
     * @param beerOrderDto The BeerOrderDto object containing the beer order details.
     * @return ResponseEntity containing the saved BeerOrder object and HTTP status CREATED.
     */
    @PostMapping("/orderBeer")
    public ResponseEntity<BeerOrderDto> createBeerOrder(@Valid @RequestBody BeerOrderDto beerOrderDto) {
        BeerOrder beerOrder = beerOrderService.orderBeer(beerOrderConverter.toEntity(beerOrderDto));
        return new ResponseEntity<>(beerOrderConverter.toDto(beerOrder), HttpStatus.CREATED);
    }

    @GetMapping("/findByZip")
    public ResponseEntity<List<BeerOrderDto>> findBeerOrderByZip(@RequestParam String zip) {
        List<BeerOrder> beerOrders = beerOrderService.findByZip(zip);
        return new ResponseEntity<>(beerOrderConverter.toDtoList(beerOrders), HttpStatus.OK);
    }
}

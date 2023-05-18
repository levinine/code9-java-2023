package com.code9.beershop.convertor;

import com.code9.beershop.dto.BeerDto;
import com.code9.beershop.dto.BeerOrderDto;
import com.code9.beershop.model.Beer;
import com.code9.beershop.model.BeerOrder;
import com.code9.beershop.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerOrderConverter {

    @Autowired
    private BeerConverter beerConverter;

    @Autowired
    private BeerService beerService;

    public BeerOrder toEntity(BeerOrderDto beerOrderDto) {
        BeerOrder beerOrder = new BeerOrder();
        beerOrder.setId(beerOrderDto.getId());
        beerOrder.setName(beerOrderDto.getName());
        beerOrder.setStreet(beerOrderDto.getStreet());
        beerOrder.setZip(beerOrderDto.getZip());
        beerOrder.setCity(beerOrderDto.getCity());
        beerOrder.setCreditCardSecurityCode(beerOrderDto.getCreditCardSecurityCode());
        beerOrder.setCreditCardNumber(beerOrderDto.getCreditCardNumber());
        beerOrder.setCreditCardExpiration(beerOrderDto.getCreditCardExpiration());
        List<Beer> beers = new ArrayList<>();

        for (BeerDto beerDto : beerOrderDto.getBeersDto()) {
            beers.add(beerService.getBeerById(beerDto.getId()));
        }
        beerOrder.setBeers(beerConverter.toEntityList((beerOrderDto.getBeersDto())));
        return beerOrder;
    }

    public BeerOrderDto toDto(BeerOrder beerOrder) {
        BeerOrderDto beerOrderDto = new BeerOrderDto();
        beerOrderDto.setId(beerOrder.getId());
        beerOrderDto.setName(beerOrder.getName());
        beerOrderDto.setStreet(beerOrder.getStreet());
        beerOrderDto.setZip(beerOrder.getZip());
        beerOrderDto.setCity(beerOrder.getCity());
        beerOrderDto.setCreditCardSecurityCode(beerOrder.getCreditCardSecurityCode());
        beerOrderDto.setCreditCardNumber(beerOrder.getCreditCardNumber());
        beerOrderDto.setCreditCardExpiration(beerOrder.getCreditCardExpiration());
        beerOrderDto.setBeersDto(beerConverter.toDtoList(beerOrder.getBeers()));
        return beerOrderDto;
    }

    public List<BeerOrderDto> toDtoList(List<BeerOrder> beerOrders) {
        List<BeerOrderDto> beerOrderDtos = new ArrayList<>();
        for (BeerOrder beerOrder : beerOrders) {
            beerOrderDtos.add(toDto(beerOrder));
        }
        return beerOrderDtos;
    }
}

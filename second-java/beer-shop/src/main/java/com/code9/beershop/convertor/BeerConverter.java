package com.code9.beershop.convertor;

import com.code9.beershop.dto.BeerDto;
import com.code9.beershop.model.Beer;
import com.code9.beershop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerConverter {

    @Autowired
    private IngredientConverter ingredientConverter;

    @Autowired
    private IngredientService ingredientService;

    public BeerDto toDto(Beer beer) {
        BeerDto beerDto = new BeerDto();
        beerDto.setId(beer.getId());
        beerDto.setName(beer.getName());
        beerDto.setIngredients(ingredientConverter.toDtoList(beer.getIngredients()));
        return beerDto;
    }

    public Beer toEntity(BeerDto beerDto) {
        Beer beer = new Beer();
        beer.setId(beerDto.getId());
        beer.setName(beerDto.getName());
        beer.setIngredients(ingredientService.saveAll(ingredientConverter.toEntityList(beerDto.getIngredients())));
        return beer;
    }

    public List<BeerDto> toDtoList(List<Beer> beers) {
        List<BeerDto> dtos = new ArrayList<>();
        for (Beer beer: beers) {
            dtos.add(toDto(beer));
        }
        return dtos;
    }

    public List<Beer> toEntityList(List<BeerDto> beersDtos) {
        List<Beer> beers = new ArrayList<>();
        for (BeerDto beerDto : beersDtos) {
            beers.add(toEntity(beerDto));
        }
        return beers;
    }

}

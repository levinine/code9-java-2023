package com.code9.beershop.controller;

import com.code9.beershop.convertor.BeerConverter;
import com.code9.beershop.dto.BeerDto;
import com.code9.beershop.model.Beer;
import com.code9.beershop.service.BeerService;
import com.code9.beershop.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beers")
@Validated
public class BeerController {

    @Autowired
    private BeerService beerService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private BeerConverter beerConverter;

    /**
     * Retrieves all beers.
     *
     * @return ResponseEntity containing the list of all beers and HTTP status OK.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<BeerDto>> getAllBeers() {
        List<BeerDto> beersDtos = beerConverter.toDtoList(beerService.getAllBeers());
        return new ResponseEntity<>(beersDtos, HttpStatus.OK);
    }

    /**
     * Saves a new beer.
     *
     * @param beerDto The BeerDto object containing the beer details.
     * @return ResponseEntity containing the saved beer and HTTP status CREATED.
     */
    @PostMapping("/saveBeer")
    public ResponseEntity<BeerDto> saveBeer(@Valid @RequestBody BeerDto beerDto) {
        Beer beer = beerService.saveBeer(beerConverter.toEntity(beerDto));
        return new ResponseEntity<>(beerConverter.toDto(beer),HttpStatus.CREATED);
    }
}

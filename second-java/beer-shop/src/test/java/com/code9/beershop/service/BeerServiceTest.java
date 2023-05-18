package com.code9.beershop.service;

import com.code9.beershop.model.Beer;
import com.code9.beershop.repository.BeerRepository;
import com.code9.beershop.repository.IngredientRepository;
import com.code9.beershop.service.impl.BeerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository beerRepository;

    @Mock
    private IngredientRepository ingredientRepository;

    @InjectMocks
    private BeerService beerService = new BeerServiceImpl();

    @Test
    public void testGetAllBeers() {
        // Creating expected data
        List<Beer> expectedBeers = Collections.singletonList(new Beer());

        // Stubbing the behavior of the beerRepository.findAll() method
        when(beerRepository.findAll()).thenReturn(expectedBeers);

        // Calling the actual method on the service implementation
        List<Beer> actualBeers = beerService.getAllBeers();

        // Verifying the expected result
        assertEquals(expectedBeers, actualBeers);

        // Verifying that the beerRepository.findAll() method was called once
        verify(beerRepository, times(1)).findAll();
    }

    @Test
    public void testGetBeerById() {
        // Creating expected data
        Long beerId = 1L;
        Beer expectedBeer = new Beer();

        // Stubbing the behavior of the beerRepository.findById() method
        when(beerRepository.findById(beerId)).thenReturn(Optional.of(expectedBeer));

        // Calling the actual method on the service implementation
        Beer actualBeer = beerService.getBeerById(beerId);

        // Verifying the expected result
        assertEquals(expectedBeer, actualBeer);

        // Verifying that the beerRepository.findById() method was called once with the correct argument
        verify(beerRepository, times(1)).findById(beerId);
    }

    @Test
    public void testSaveBeer() {
        // Creating input data
        Beer beer = new Beer();
        beer.setName("Test Beer");
        beer.setIngredients(Collections.emptyList());

        // Creating expected data
        Beer expectedBeer = new Beer();
        expectedBeer.setName("Test Beer");
        expectedBeer.setCreatedAt(LocalDateTime.now());

        // Stubbing the behavior of the beerRepository.save() method
        when(beerRepository.save(beer)).thenReturn(expectedBeer);

        // Calling the actual method on the service implementation
        Beer actualBeer = beerService.saveBeer(beer);

        // Verifying the expected result
        assertEquals(expectedBeer, actualBeer);

        // Verifying that the beerRepository.save() method was called once with any Beer object as the argument
        verify(beerRepository, times(1)).save(any(Beer.class));
    }
}
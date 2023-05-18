package com.code9.beershop.controller;

import com.code9.beershop.convertor.BeerConverter;
import com.code9.beershop.dto.BeerDto;
import com.code9.beershop.dto.IngredientDto;
import com.code9.beershop.model.Beer;
import com.code9.beershop.model.Ingredient;
import com.code9.beershop.service.BeerService;
import com.code9.beershop.service.IngredientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(BeerController.class) // Mockito: Annotation to configure the test for the BeerController class
public class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc; // Mockito: MockMvc object to perform HTTP requests

    @MockBean // Mockito: Annotation to create a mock of the BeerService dependency
    private BeerService beerService;

    @MockBean // Mockito: Annotation to create a mock of the IngredientService dependency
    private IngredientService ingredientService;

    @MockBean
    private BeerConverter beerConverter;

    @Test // JUnit: Annotation to mark a test method
    void testGetAllBeers() throws Exception {
        // Mock data
        List<Beer> beers = new ArrayList<>();
        beers.add(new Beer(1L, LocalDateTime.now(), "Beer1 name", Arrays.asList(new Ingredient(1L, "Ingredient 1"))));
        beers.add(new Beer(2L, LocalDateTime.now(), "Beer2 name", Arrays.asList(new Ingredient(1L, "Ingredient 1"))));

        List<BeerDto> beerDtos = new ArrayList<>();
        beerDtos.add(new BeerDto(1L, "Beer1 name", Arrays.asList(new IngredientDto(1L, "Ingredient 1"))));
        beerDtos.add(new BeerDto(2L,"Beer2 name", Arrays.asList(new IngredientDto(1L, "Ingredient 1"))));

        // Mock behaviours
        when(beerService.getAllBeers()).thenReturn(beers);
        when(beerConverter.toDtoList(beers)).thenReturn(beerDtos);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/beers/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists());

        // Mockito: Verify the service method was called
        verify(beerService, times(1)).getAllBeers();
    }

    @Test
    void testSaveBeer() throws Exception {
        Beer savedBeer = new Beer();
        savedBeer.setId(1L);
        savedBeer.setName("Test Beer");
        savedBeer.setIngredients(Arrays.asList(new Ingredient(1L, "Ingredient 1")));

        BeerDto beerDto = new BeerDto();
        beerDto.setId(1L);
        beerDto.setName("Test Beer");
        beerDto.setIngredients(Arrays.asList(new IngredientDto(1L, "Ingredient 1")));

        when(beerConverter.toEntity(any(BeerDto.class))).thenReturn(savedBeer);
        when(beerService.saveBeer(any(Beer.class))).thenReturn(savedBeer);
        when(beerConverter.toDto(savedBeer)).thenReturn(beerDto);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/beers/saveBeer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Beer\",\"ingredients\": [{\"id\":\"1\",\"name\": \"Ingredient 1\"}]}"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

        // Verify the service method was called
        verify(beerService, times(1)).saveBeer(any(Beer.class));
    }
}

package com.code9.beershop.service.impl;

import com.code9.beershop.model.Ingredient;
import com.code9.beershop.repository.IngredientRepository;
import com.code9.beershop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the IngredientService interface.
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    /**
     * Saves a list of ingredients.
     *
     * @param ingredients The list of ingredients to be saved.
     * @return The saved list of ingredients.
     */
    @Override
    public List<Ingredient> saveAll(List<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }
}

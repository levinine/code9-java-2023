package com.code9.beershop.service;

import com.code9.beershop.model.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> saveAll(List<Ingredient> ingredients);
}

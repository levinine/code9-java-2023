package com.code9.beershop.convertor;


import com.code9.beershop.dto.IngredientDto;
import com.code9.beershop.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientConverter {

    public IngredientDto toDto(Ingredient ingredient) {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());
        ingredientDto.setName(ingredient.getName());
        return ingredientDto;
    }

    public Ingredient toEntity(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDto.getId());
        ingredient.setName(ingredientDto.getName());
        return ingredient;
    }

    public List<IngredientDto> toDtoList(List<Ingredient> ingredients) {
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        for (Ingredient i:ingredients) {
            ingredientDtos.add(toDto(i));
        }
        return ingredientDtos;
    }

    public List<Ingredient> toEntityList(List<IngredientDto> ingredientDtos) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDto i:ingredientDtos) {
            ingredients.add(toEntity(i));
        }
        return ingredients;
    }

}

package com.code9.beershop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

    private Long id;

    private String name;

    @NotEmpty
    private List<IngredientDto> ingredients;
}

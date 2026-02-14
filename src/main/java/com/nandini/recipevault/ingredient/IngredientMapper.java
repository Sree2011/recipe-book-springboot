package com.nandini.recipevault.ingredient;

import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientDto toDTO(Ingredient ingredient);
    Ingredient toEntity(IngredientDto ingredientDTO);

    List<IngredientDto> toDTOList(List<Ingredient> ingredients);
    List<Ingredient> toEntityList(List<IngredientDto> ingredientDTOs);
}

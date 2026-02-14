package com.nandini.recipevault.recipe;

import com.nandini.recipevault.ingredient.IngredientMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = IngredientMapper.class)
public interface RecipeMapper {
    RecipeDto toDTO(Recipe recipe);
    Recipe toEntity(RecipeDto recipeDTO);

    List<RecipeDto> toDTOList(List<Recipe> recipes);
    List<Recipe> toEntityList(List<RecipeDto> recipeDTOs);
}
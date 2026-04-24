package com.sai.scalingservice.service;

import com.sai.scalingservice.client.RecipeClient;
import com.sai.scalingservice.dto.RecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScalingService {

    private final RecipeClient recipeClient;

    public RecipeDTO getScaledRecipe(Long id, int targetPortions) {
        // 1. Fetch the original recipe from RecipeCore (8081)
        RecipeDTO recipe = recipeClient.getRecipeById(id);

        // 2. Calculate the Conversion Factor
        // We cast to double to ensure precision (e.g., 6/4 = 1.5)
        double factor = (double) targetPortions / recipe.getServings();

        // 3. Apply the factor to each ingredient
        recipe.getIngredients().forEach(ingredient -> {
            double originalQuantity = ingredient.getQuantity();
            ingredient.setQuantity(originalQuantity * factor);
        });

        // 4. Update the servings count in the DTO to the new target
        recipe.setServings(targetPortions);

        return recipe;
    }
}
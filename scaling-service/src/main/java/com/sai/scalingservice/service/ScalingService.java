package com.sai.scalingservice.service;

import com.sai.scalingservice.client.RecipeClient;
import com.sai.scalingservice.dto.RecipeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, String> requiredCalories(Double height, Double weight, Double age, String activity) {
        Double BMR = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        Double Tdee = 0.0;
        if (activity.equalsIgnoreCase("sedentary")) {
            Tdee = BMR * 1.2;
        } else if (activity.equalsIgnoreCase("Lightly Active")) {
            Tdee = BMR * 1.375;

        } else if (activity.equalsIgnoreCase("Moderately Active")) {
            Tdee = BMR * 1.55;
        } else if (activity.equalsIgnoreCase("very active")) {
            Tdee = BMR * 1.725;
        } else if (activity.equalsIgnoreCase("hard exercise")) {
            Tdee = BMR * 1.9;
        }

        Map<String, String> result = new HashMap<>();
        result.put("loss", (Tdee - 500) + " - " + (Tdee - 300));
        result.put("gain", (Tdee + 300) + " - " + (Tdee + 500));

        return result;

    }



}
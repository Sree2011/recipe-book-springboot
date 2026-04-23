package com.sai.fullstack.recipe_service.dto;



/**
 * Data Transfer Object for Ingredients.
 * Using Java Records for immutability and clean interservice communication.
 */

public record IngredientDTO(
        String name,      // e.g., "Turmeric", "Basmati Rice"
        double quantity,  // e.g., 0.5, 2.0
        String unit       // e.g., "tsp", "cup", "kg"
) {}


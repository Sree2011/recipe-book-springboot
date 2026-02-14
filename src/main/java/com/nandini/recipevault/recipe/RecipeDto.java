package com.nandini.recipevault.recipe;

import com.nandini.recipevault.ingredient.IngredientDto;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.List;

@Schema(description = "Schema representing a recipe with name, description, instructions, and ingredients")
public class RecipeDto {

        @Schema(description = "Name of the recipe", example = "Chocolate Cake")
        private Long id;

        @Schema(description = "Name of the recipe", example = "Chocolate Cake")
        private String name;

        @Schema(description = "Short description of the recipe", example = "A rich and moist chocolate cake")
        private String description;

        @Schema(description = "Step-by-step instructions to prepare the recipe", example = "Mix flour, sugar, cocoa...")
        private String instructions;

        @Schema(description = "List of ingredients required for the recipe")
        private List<IngredientDto> ingredients;

        public RecipeDto(String name, String description, String instructions, List<IngredientDto> ingredients) {
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }



    @Override
    public String toString() {
        return "RecipeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", instructions='" + instructions + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }


}
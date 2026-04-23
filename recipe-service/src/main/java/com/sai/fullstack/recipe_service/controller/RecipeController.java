package com.sai.fullstack.recipe_service.controller;

import com.sai.fullstack.recipe_service.entity.Recipe;
import com.sai.fullstack.recipe_service.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping("/create")
    @Operation(summary = "Create a new recipe and sync ingredients to master list")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe recipe_out = service.createRecipe(recipe);
        return ResponseEntity.ok(recipe_out);
    }

    @GetMapping("/getall")
    @Operation(summary = "Get all recipes")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> all_recipes = service.getAllRecipes();
        return ResponseEntity.ok(all_recipes);
    }

    // Standardized to use PathVariable for cleaner URLs
    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by id")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe byId = service.getRecipeById(id);
        return ResponseEntity.ok(byId);
    }

    // Standardized to use PathVariable
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recipe by id")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        String message = service.deleteRecipe(id);
        return ResponseEntity.ok(message);
    }

    /**
     * NEW ENDPOINT: Link Master Ingredient to Recipe IDs
     * This fulfills the requirement of finding all recipes containing a specific ingredient.
     */
    @GetMapping("/ingredient/{name}/recipes")
    @Operation(summary = "Get all Recipe IDs that contain a specific master ingredient")
    public ResponseEntity<List<Long>> getRecipeIdsByIngredient(@PathVariable String name) {
        List<Long> recipeIds = service.getRecipeIdsByIngredient(name.toLowerCase());
        return ResponseEntity.ok(recipeIds);
    }
}
package com.sai.recipeservice.controller;

import com.sai.recipeservice.entity.Recipe;
import com.sai.recipeservice.service.RecipeService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import static com.sai.recipeservice.exception.SwaggerConstants.*;

@RestController
@RequestMapping("/api/recipes")
@ApiResponse(responseCode = "404",description = "Recipe not found", content = @Content(examples=@ExampleObject(value=RECIPE404)))
@ApiResponse(responseCode = "500",description = "Internal Server error", content =@Content(examples=@ExampleObject(value=RECIPE500)))
public class RecipeController {

    @Autowired
    private RecipeService service;

    @PostMapping("/create")
    @Operation(summary = "Create a new recipe and sync ingredients to master list")
    @ApiResponse(responseCode = "201",description = "Recipe Created", content =@Content(examples=@ExampleObject(value=RECIPE500)))
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe recipe_out = service.createRecipe(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe_out);
    }

    @GetMapping("/getall")
    @Operation(summary = "Get all recipes")
    @ApiResponse(responseCode = "200",description = "All Recipes returned", content =@Content(examples=@ExampleObject(value=ALLRECIPES)))
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> all_recipes = service.getAllRecipes();
        return ResponseEntity.ok(all_recipes);
    }

    // Standardized to use PathVariable for cleaner URLs
    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by id")
    @ApiResponse(responseCode = "200",description = "Recipe found", content =@Content(examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe byId = service.getRecipeById(id);
        return ResponseEntity.ok(byId);
    }

    // Standardized to use PathVariable
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recipe by id")
    @ApiResponse(responseCode = "204",description = "Deleted recipe successfully", content =@Content(examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        String message = service.deleteRecipe(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }

    /**
     * NEW ENDPOINT: Link Master Ingredient to Recipe IDs
     * This fulfills the requirement of finding all recipes containing a specific ingredient.
     */
    @GetMapping("/ingredient/{name}/recipes")
    @Operation(summary = "Get all Recipe IDs that contain a specific master ingredient")
    @ApiResponse(responseCode = "200",description = "All Recipes returned", content =@Content(examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<List<Long>> getRecipeIdsByIngredient(@PathVariable String name) {
        List<Long> recipeIds = service.getRecipeIdsByIngredient(name.toLowerCase());
        return ResponseEntity.ok(recipeIds);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a recipe")
    @ApiResponse(responseCode = "200",description = "All Recipes returned", content =@Content(examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<Recipe> updateRecipeById(@PathVariable Long id, @RequestBody Recipe recipeu){
        Recipe r = service.updateRecipe(id,recipeu);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/masteringredients")
    @Operation(summary = "Get all the master ingredients available")
    @ApiResponse(responseCode = "200",description = "All Recipes returned", content =@Content(examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<List<String>> getAllMasterIngredients() {
        List<String> master = service.getAllMasterIngredients();
        return ResponseEntity.ok(master);
    }
}
package com.sai.recipeservice.controller;

import com.sai.recipeservice.entity.Recipe;
import com.sai.recipeservice.service.RecipeService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Map;

import static com.sai.recipeservice.exception.SwaggerConstants.*;

/**
 * REST controller exposing endpoints for managing the recipe lifecycle
 * and orchestrating automated master ingredient synchronization.
 * <p>
 * This controller serves as the primary entry boundary for recipe data and interacts
 * directly with the underlying {@code RecipeService} to persist and fetch records.
 * All endpoint responses are configured with SpringDoc OpenAPI metadata for documentation rendering.
 * </p>
 *
 * @author Sree Sai Nandini Gundraju
 * @version 1.0
 */
@RestController
@RequestMapping("/api/recipes")
@ApiResponse(responseCode = "404",description = "Recipe not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
        examples=@ExampleObject(value=RECIPE404)))
@ApiResponse(responseCode = "500",description = "Internal Server error", content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value=RECIPE500)))
public class RecipeController {

    @Autowired
    private RecipeService service;

    /**
     * Persists a new recipe entity and synchronizes its constituent components
     * with the global master ingredient dictionary registry.
     *
     * @param recipe the structural recipe data object containing name, servings,
     * instructions, and relational ingredient metadata arrays
     * @return a {@code ResponseEntity} wrapping the newly created {@code Recipe}
     * and an HTTP status of {@code 201 Created}
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new recipe and sync ingredients to master list")
    @ApiResponse(responseCode = "201",description = "Recipe Created", content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        Recipe recipe_out = service.createRecipe(recipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipe_out);
    }

    /**
     * Retrieves all recipe records present in the database.
     * <p>
     * This endpoint fetches the complete list of recipes, including their nested 
     * contextual ingredients and mapped master definitions.
     * </p>
     *
     * @return a {@code ResponseEntity} containing a {@code List} of all managed {@code Recipe} entities 
     * and an HTTP status of {@code 200 OK}
     */
    @GetMapping("/getall")
    @Operation(summary = "Get all recipes")
    @ApiResponse(responseCode = "200",description = "All Recipes returned", content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value=ALLRECIPES)))
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> all_recipes = service.getAllRecipes();
        return ResponseEntity.ok(all_recipes);
    }

    /**
     * Retrieves a specific recipe entity by its unique primary key identifier.
     *
     * @param id the unique database {@code Long} ID of the target recipe
     * @return a {@code ResponseEntity} wrapping the located {@code Recipe} entity 
     * and an HTTP status of {@code 200 OK}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by id")
    @ApiResponse(responseCode = "200",description = "Recipe found", content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe byId = service.getRecipeById(id);
        return ResponseEntity.ok(byId);
    }

    /**
     * Removes a specific recipe entity from the system by its unique identifier.
     * <p>
     * Dependent child ingredients mapped to this recipe container are handled according 
     * to the underlying JPA cascade configuration boundaries.
     * </p>
     *
     * @param id the unique database {@code Long} ID of the recipe to be deleted
     * @return a {@code ResponseEntity} containing an operational status message string 
     * and an HTTP status of {@code 204 No Content} upon a successful deletion transaction
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recipe by id")
    @ApiResponse(responseCode = "204",description = "Deleted recipe successfully", content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        String message = service.deleteRecipe(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    }

    /**
     * NEW ENDPOINT: Link Master Ingredient to Recipe IDs
     * This fulfills the requirement of finding all recipes containing a specific ingredient.
     * <p>
     * The input parameter string name is case-normalized to lower-case characters prior 
     * to querying the service engine layer to maintain data retrieval parity.
     * </p>
     * * @param name the textual string name of the master ingredient to search against
     * @return a {@code ResponseEntity} containing a {@code List} of unique {@code Long} recipe IDs 
     * that contain the specified master ingredient, alongside an HTTP status of {@code 200 OK}
     */
    @GetMapping("/ingredient/{name}/recipes")
    @Operation(summary = "Get all Recipe IDs that contain a specific master ingredient")
    @ApiResponse(responseCode = "200",description = "All Recipes returned", content =@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,examples=@ExampleObject(value=ALLRECIPES)))
    public ResponseEntity<List<Long>> getRecipeIdsByIngredient(@PathVariable String name) {
        List<Long> recipeIds = service.getRecipeIdsByIngredient(name.toLowerCase());
        return ResponseEntity.ok(recipeIds);
    }

    /**
     * Updates specific structural details of an existing recipe record.
     * <p>
     * This applies partial or complete updates to the recipe target identified by the path parameter 
     * based on the incoming request body payload wrapper.
     * </p>
     *
     * @param id the unique database {@code Long} ID of the recipe to modify
     * @param recipe the incoming recipe data structure carrying the updated field mutations
     * @return a {@code ResponseEntity} wrapping the updated, managed {@code Recipe} entity instance 
     * and an HTTP status of {@code 200 OK}
     */
    @PatchMapping("/{id}")
    @Operation(summary = "Update a recipe")
    @ApiResponse(responseCode = "200",description = "All Recipes returned", content =@Content(examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<Recipe> updateRecipeById(@PathVariable Long id, @RequestBody Recipe recipe){
        Recipe r = service.updateRecipe(id,recipe);
        return ResponseEntity.ok(r);
    }

    /**
     * Extracts the complete list of unique ingredients currently cataloged in the 
     * global master inventory dictionary lookup table.
     *
     * @return a {@code ResponseEntity} wrapping a {@code List} of all unique master ingredient 
     * name strings present in the system, with an HTTP status of {@code 200 OK}
     */
    @GetMapping("/masteringredients")
    @Operation(summary = "Get all the master ingredients available")
    @ApiResponse(responseCode = "200",description = "All Recipes returned", content =@Content(examples=@ExampleObject(value=RECIPE200)))
    public ResponseEntity<List<String>> getAllMasterIngredients() {
        List<String> master = service.getAllMasterIngredients();
        return ResponseEntity.ok(master);
    }


}

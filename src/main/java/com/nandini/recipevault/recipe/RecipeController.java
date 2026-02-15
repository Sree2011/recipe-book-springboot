package com.nandini.recipevault.recipe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nandini.recipevault.config.Constants.*;

@RestController
@RequestMapping("/api/recipes")
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
                        examples = @ExampleObject(name = "Recipe 500", value = RECIPE500)))
@ApiResponse(responseCode = "404", description = "Recipe Not found",
                content = @Content(mediaType = "application/json",
                        examples = @ExampleObject(name = "Recipe 404", value = RECIPE404)))

public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    // Create a new recipe
    @Operation(summary = "Create a Recipe")
    @ApiResponse(responseCode = "200", description = "Recipe created successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Recipe Example", value = EXAMPLERECIPEJSON)))
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.toEntity(recipeDto);
        Recipe saved = recipeService.saveRecipe(recipe);
        return recipeMapper.toDTO(saved);
    }

    // Get all recipes
    @Operation(summary = "Get All Recipes")
    @ApiResponse(responseCode = "200", description = "All recipes retrieved successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Recipe Example", value = EXAMPLERECIPEJSON)))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes()
                .stream()
                .map(recipeMapper::toDTO)
                .toList();
    }

    // Get recipe by ID
    @Operation(summary = "Get Recipe by ID")
    @ApiResponse(responseCode = "200", description = "Recipe retrieved successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Recipe Example", value = EXAMPLERECIPEJSON)))
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecipeDto getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return recipeMapper.toDTO(recipe);
    }

    // Delete recipe by ID
    @Operation(summary = "Delete Recipe by ID")
    @ApiResponse(responseCode = "200", description = "Recipe deleted successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Delete recipe", value = RECIPE204)))
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok(RECIPE204);
    }
}
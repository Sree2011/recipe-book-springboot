package com.nandini.recipevault.recipe;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.nandini.recipevault.config.Constants.*;


@RestController
@RequestMapping("/recipes")
@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",
        examples = {@ExampleObject(name = "Recipe 500", value = Recipe500)}))
@ApiResponse(responseCode = "404", description = "Recipe Not found",
        content = @Content(
        mediaType = "application/json",
        examples = {
                @ExampleObject(name = "Recipe 404", value = Recipe404)
        }
))
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    // Create a new recipe
    @Operation(summary = "Create a Recipe")
    @ApiResponse(responseCode = "200", description = "Recipe Created Successfully", content = @Content(
            mediaType = "application/json",
            examples = {
                    @ExampleObject(name = "Recipe Example", value = exampleRecipeJson)
            }
    )
)
    @ApiResponse(responseCode = "404", description = "Recipe Created Successfully", content = @Content(
            mediaType = "application/json",
            examples = {
                    @ExampleObject(name = "Recipe Example", value = Recipe404)
            }
    ))
    @PostMapping
    public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.toEntity(recipeDto);
        Recipe saved = recipeService.saveRecipe(recipe);
        return recipeMapper.toDTO(saved);
    }

    // Get all recipes
    @Operation(summary = "Get ALl Recipes")
    @ApiResponse(responseCode = "200", description = "All recipes retrieved successfully")
    @GetMapping
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes()
                .stream()
                .map(recipeMapper::toDTO)
                .toList();
    }

    // Get recipe by ID
    @Operation(summary = "Get Recipe by ID")
    @ApiResponse(responseCode = "200", description = "Recipe retrieved successfully")
    @GetMapping("/{id}")
    public RecipeDto getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return recipeMapper.toDTO(recipe);
    }

    // Delete recipe by ID
    @Operation(summary = "Get Recipe by ID")
    @ApiResponse(responseCode = "200", description = "Recipe deleted successfully")
    @ApiResponse(responseCode = "200", description = "Recipe Created Successfully", content = @Content(
            mediaType = "application/json",
            examples = {
                    @ExampleObject(name = "Recipe Example", value = exampleRecipeJson)
            }
    )
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
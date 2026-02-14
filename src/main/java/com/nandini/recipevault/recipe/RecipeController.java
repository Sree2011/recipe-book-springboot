package com.nandini.recipevault.recipe;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    public RecipeController(RecipeService recipeService, RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.recipeMapper = recipeMapper;
    }

    // Create a new recipe
    @PostMapping
    public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.toEntity(recipeDto);
        Recipe saved = recipeService.saveRecipe(recipe);
        return recipeMapper.toDTO(saved);
    }

    // Get all recipes
    @GetMapping
    public List<RecipeDto> getAllRecipes() {
        return recipeService.getAllRecipes()
                .stream()
                .map(recipeMapper::toDTO)
                .toList();
    }

    // Get recipe by ID
    @GetMapping("/{id}")
    public RecipeDto getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        return recipeMapper.toDTO(recipe);
    }

    // Delete recipe by ID
    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }
}
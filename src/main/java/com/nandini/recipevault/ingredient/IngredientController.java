package com.nandini.recipevault.ingredient;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    // Create a new ingredient
    @PostMapping
    public IngredientDto createIngredient(@RequestBody IngredientDto ingredientDto) {
        Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
        Ingredient saved = ingredientService.saveIngredient(ingredient);
        return ingredientMapper.toDTO(saved);
    }

    // Get all ingredients
    @GetMapping
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAllIngredients()
                .stream()
                .map(ingredientMapper::toDTO)
                .toList();
    }

    // Get ingredient by ID
    @GetMapping("/{id}")
    public IngredientDto getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ingredientMapper.toDTO(ingredient);
    }

    // Delete ingredient by ID
    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
    }
}
package com.nandini.recipevault.ingredient;

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
@RequestMapping("/api/ingredients")
@ApiResponse(responseCode = "500", description = "Internal Server Error",
                content = @Content(mediaType = "application/json",
                        examples = @ExampleObject(name = "Ingredient 500", value = RECIPE500)))
@ApiResponse(responseCode = "404", description = "Ingredient Not Found",
                content = @Content(mediaType = "application/json",
                        examples = @ExampleObject(name = "Ingredient 404", value = INGREDIENT404)))

public class IngredientController {

    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    public IngredientController(IngredientService ingredientService, IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.ingredientMapper = ingredientMapper;
    }

    // Create a new ingredient
    @Operation(summary = "Create an Ingredient")
    @ApiResponse(responseCode = "200", description = "Ingredient created successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Ingredient Example", value = EXAMPLEINGREDIENTJSON)))
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto createIngredient(@RequestBody IngredientDto ingredientDto) {
        Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);
        Ingredient saved = ingredientService.saveIngredient(ingredient);
        return ingredientMapper.toDTO(saved);
    }

    // Get all ingredients
    @Operation(summary = "Get All Ingredients")
    @ApiResponse(responseCode = "200", description = "All ingredients retrieved successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Ingredient Example", value = EXAMPLEINGREDIENTJSON)))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAllIngredients()
                .stream()
                .map(ingredientMapper::toDTO)
                .toList();
    }

    // Get ingredient by ID
    @Operation(summary = "Get Ingredient by ID")
    @ApiResponse(responseCode = "200", description = "Ingredient retrieved successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Ingredient Example", value = EXAMPLEINGREDIENTJSON)))
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public IngredientDto getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ingredientMapper.toDTO(ingredient);
    }

    // Delete ingredient by ID
    @Operation(summary = "Delete Ingredient by ID")
    @ApiResponse(responseCode = "200", description = "Ingredient deleted successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Delete ingredient", value = INGREDIENT204)))
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredientById(id);
        return ResponseEntity.ok(INGREDIENT204);
    }
}
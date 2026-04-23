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


    @PostMapping("/create/")
    @Operation(summary="create recipe")
    public ResponseEntity<Recipe>  createRecipe(@RequestBody Recipe recipe){
        Recipe recipe_out = service.createRecipe(recipe);
        return ResponseEntity.ok(recipe_out);
    }

    @GetMapping("/getall/")
    @Operation(summary = "get all recipes")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
        List<Recipe> all_recipes = service.getAllRecipes();
        return ResponseEntity.ok(all_recipes);
    }

    @GetMapping("/getbyid/")
    @Operation(summary = "get recipe by id")
    public ResponseEntity<Recipe> getRecipeById(@RequestParam Long id){
        Recipe byId = service.getRecipeById(id);
        return ResponseEntity.ok(byId);
    }

    @DeleteMapping("/delete/")
    @Operation(summary = "delete recipe by id")
    public ResponseEntity<String> deleteRecipe(@RequestParam Long id){
        String name = service.deleteRecipe(id);
        return ResponseEntity.ok(name);
    }




//    @GetMapping("/{id}/scale")
//    public ResponseEntity<List<IngredientDTO>> scaleRecipe(@PathVariable Long id, @RequestParam int portions) {
//        List<IngredientDTO> scaledIng = service.getScaledIngredients(id, portions);
//        return ResponseEntity.ok(scaledIng);
//    }
}
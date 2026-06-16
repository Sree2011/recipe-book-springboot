package com.sai.recipeservice.controller;

import com.sai.recipeservice.entity.FoodCalories;
import com.sai.recipeservice.service.FoodCalorieService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/foods")
public class FoodCaloriesController {

    private final FoodCalorieService service;

    public FoodCaloriesController(FoodCalorieService service) {
        this.service = service;
    }


    @GetMapping("/getall")
    @Operation(summary = "Get All foods")
    public List<FoodCalories> getAllFoods() {
        return service.getAllFoods();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get food by name")
    public FoodCalories getFoodByName(@PathVariable String name) {
        return service.getByName(name)
                .orElseThrow(() -> new RuntimeException("Food not found: " + name));
    }

    @GetMapping("/low-calorie")
    @Operation(summary = "Get food having calories lesser than a number")
    public List<FoodCalories> getLowCalorieFoods(@RequestParam Double maxCalories) {
        return service.getLowCalorieFoods(maxCalories);
    }

    @GetMapping("/high-protein")
    @Operation(summary = "Get food having protein more than a number")
    public List<FoodCalories> getHighProteinFoods(@RequestParam Double minProtein) {
        return service.getHighProteinFoods(minProtein);
    }

    @GetMapping("/search")
    @Operation(summary = "search recipes by keyword")
    public List<FoodCalories> searchFoods(@RequestParam String keyword) {
        return service.searchByKeyword(keyword);
    }

    @PostMapping("/create")
    @Operation(summary = "Add recipe with calories")
    public FoodCalories addFood(@RequestBody FoodCalories foodCalories) {
        return service.saveFood(foodCalories);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete food")
    public String deleteFood(@PathVariable Long id){
        return service.deleteFood(id);
    }

    @GetMapping("/recipe/{id}/nutrition")
    @Operation(summary = "Get the nutrition facts of a recipe")
    public Map<String, Double> getRecipeNutrition(@PathVariable Long id) {
        return service.getRecipeNutrition(id);
    }

    @GetMapping("/required-calories")
    @Operation(summary = "calculate required calories based on height, weight, age and activity")
    public Map<String, String> requiredCalories(
            @RequestParam Double height,
            @RequestParam Double weight,
            @RequestParam Double age,
            @RequestParam String activity){
        return service.requiredCalories(height, weight, age, activity);
    }






}


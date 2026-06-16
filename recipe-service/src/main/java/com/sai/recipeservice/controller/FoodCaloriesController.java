package com.sai.recipeservice.controller;

import com.sai.recipeservice.entity.FoodCalories;
import com.sai.recipeservice.service.FoodCalorieService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodCaloriesController {

    private final FoodCalorieService service;

    public FoodCaloriesController(FoodCalorieService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public List<FoodCalories> getAllFoods() {
        return service.getAllFoods();
    }

    @GetMapping("/{name}")
    public FoodCalories getFoodByName(@PathVariable String name) {
        return service.getByName(name)
                .orElseThrow(() -> new RuntimeException("Food not found: " + name));
    }

    @GetMapping("/low-calorie")
    public List<FoodCalories> getLowCalorieFoods(@RequestParam Double maxCalories) {
        return service.getLowCalorieFoods(maxCalories);
    }

    @GetMapping("/high-protein")
    public List<FoodCalories> getHighProteinFoods(@RequestParam Double minProtein) {
        return service.getHighProteinFoods(minProtein);
    }

    @GetMapping("/search")
    public List<FoodCalories> searchFoods(@RequestParam String keyword) {
        return service.searchByKeyword(keyword);
    }

    @PostMapping("/create")
    public FoodCalories addFood(@RequestBody FoodCalories foodCalories) {
        return service.saveFood(foodCalories);
    }

    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable Long id){
        return service.deleteFood(id);
    }
}


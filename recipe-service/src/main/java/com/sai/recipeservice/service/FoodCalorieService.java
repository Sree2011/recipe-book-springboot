package com.sai.recipeservice.service;

import com.sai.recipeservice.entity.FoodCalories;
import com.sai.recipeservice.entity.Ingredient;
import com.sai.recipeservice.entity.Recipe;
import com.sai.recipeservice.repository.FoodCaloriesRepository;
import com.sai.recipeservice.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FoodCalorieService {

    private final FoodCaloriesRepository repository;
    private final RecipeRepository recipeRepo;
    public FoodCalorieService(FoodCaloriesRepository repository, RecipeRepository recipeRepo) {
        this.repository = repository;
        this.recipeRepo = recipeRepo;
    }

    public Optional<FoodCalories> getByName(String foodName) {
        return repository.findByFoodName(foodName);
    }

    public List<FoodCalories> getLowCalorieFoods(Double maxCalories) {
        return repository.findByCaloriesLessThan(maxCalories);
    }

    public List<FoodCalories> getHighProteinFoods(Double minProtein) {
        return repository.findByProteinGreaterThan(minProtein);
    }

    public List<FoodCalories> searchByKeyword(String keyword) {
        return repository.findByFoodNameContainingIgnoreCase(keyword);
    }

    public FoodCalories saveFood(FoodCalories foodCalories) {
        return repository.save(foodCalories);
    }

    public List<FoodCalories> getAllFoods() {
        return repository.findAll();
    }

    public String deleteFood(Long id){
        repository.deleteById(id);
        return "deleted";
    }
        // New method: aggregate nutrition for a recipe
        public Map<String, Double> getRecipeNutrition(Long recipeId) {
            Recipe recipe = recipeRepo.findRecipeWithNutrition(recipeId);

            double totalCalories = 0.0;
            double totalProtein = 0.0;
            double totalCarbs = 0.0;
            double totalFat = 0.0;

            for (Ingredient ing : recipe.getIngredients()) {
                FoodCalories fc = ing.getFoodCalories();
                if (fc != null) {
                    totalCalories += fc.getCalories() != null ? fc.getCalories() : 0;
                    totalProtein += fc.getProtein() != null ? fc.getProtein() : 0;
                    totalCarbs   += fc.getCarbs()   != null ? fc.getCarbs()   : 0;
                    totalFat     += fc.getFat()     != null ? fc.getFat()     : 0;
                }
            }

            Map<String, Double> totals = new HashMap<>();
            totals.put("calories", totalCalories);
            totals.put("protein", totalProtein);
            totals.put("carbs", totalCarbs);
            totals.put("fat", totalFat);

            return totals;
        }










    }




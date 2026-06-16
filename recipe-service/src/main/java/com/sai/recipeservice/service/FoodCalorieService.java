package com.sai.recipeservice.service;

import com.sai.recipeservice.entity.FoodCalories;
import com.sai.recipeservice.repository.FoodCaloriesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FoodCalorieService {

    private final FoodCaloriesRepository repository;

    public FoodCalorieService(FoodCaloriesRepository repository) {
        this.repository = repository;
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
}


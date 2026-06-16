package com.sai.recipeservice.repository;

import com.sai.recipeservice.entity.FoodCalories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface FoodCaloriesRepository extends JpaRepository<FoodCalories, Long> {

    // Find by exact food name
    Optional<FoodCalories> findByFoodName(String foodName);

    // Find all foods with calories less than a given value
    List<FoodCalories> findByCaloriesLessThan(Double calories);

    // Find all foods with protein greater than a given value
    List<FoodCalories> findByProteinGreaterThan(Double protein);

    // Find all foods containing a keyword in the name (case-insensitive)
    List<FoodCalories> findByFoodNameContainingIgnoreCase(String keyword);
}


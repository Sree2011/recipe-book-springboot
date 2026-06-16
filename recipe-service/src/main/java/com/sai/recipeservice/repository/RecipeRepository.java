
package com.sai.recipeservice.repository;

import com.sai.recipeservice.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Data access repository interface providing abstraction layer mechanics
 * for managing {@code Recipe} entity persistence logic inside the MySQL engine.
 * <p>
 * This component serves as the core database boundary gateway for the recipe root aggregate.
 * By extending {@code JpaRepository}, it inherits robust, out-of-the-box transactional capabilities,
 * sorting frameworks, and standard relational CRUD query mechanisms mapped against 
 * the primary key type {@code Long}.
 * </p>
 *
 * @author Sree Sai Nandini Gundraju
 * @version 1.0
 * @see JpaRepository
 * @see Recipe
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r JOIN FETCH r.ingredients i JOIN FETCH i.foodCalories WHERE r.id = :id")
    Recipe findRecipeWithNutrition(Long id);
}


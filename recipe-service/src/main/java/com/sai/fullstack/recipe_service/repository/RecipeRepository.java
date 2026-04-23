package com.sai.fullstack.recipe_service.repository;


import com.sai.fullstack.recipe_service.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
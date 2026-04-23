package com.sai.fullstack.recipe_service.service;


import com.sai.fullstack.recipe_service.entity.Ingredient;
import com.sai.fullstack.recipe_service.entity.MasterIngredient;
import com.sai.fullstack.recipe_service.entity.Recipe;
import com.sai.fullstack.recipe_service.repository.MasterIngredientRepository;
import com.sai.fullstack.recipe_service.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repository;


    public List<Recipe> getAllRecipes(){
        return repository.findAll();
    }


    public Recipe getRecipeById(@RequestParam Long id){
        return repository.getReferenceById(id);
    }


    @Autowired
    private MasterIngredientRepository masterRepo; // You'll need this

    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        // We use a new object to ensure clean state
        Recipe recipeToSave = new Recipe();
        recipeToSave.setName(recipe.getName());
        recipeToSave.setInstructions(recipe.getInstructions());
        recipeToSave.setIngredients(recipe.getIngredients());

        if (recipeToSave.getIngredients() != null) {
            for (Ingredient ing : recipeToSave.getIngredients()) {

                // 1. LINK: Fix the NULL recipe_id
                ing.setRecipe(recipeToSave);

                // 2. SYNC: Add to Master table if it doesn't exist
                // This works now because the Repo uses String ID
                if (!masterRepo.existsById(ing.getName())) {
                    masterRepo.save(new MasterIngredient(ing.getName()));
                }
            }
        }

        return repository.save(recipeToSave);
    }

    public String deleteRecipe(Long id) {
        Recipe recipe = getRecipeById(id);
        repository.delete(recipe);
        return recipe.getName() + " is deleted successfully";
    }
}
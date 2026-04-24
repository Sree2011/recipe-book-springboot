package com.sai.recipeservice.service;

import com.sai.recipeservice.entity.Ingredient;
import com.sai.recipeservice.entity.MasterIngredient;
import com.sai.recipeservice.entity.Recipe;
import com.sai.recipeservice.repository.MasterIngredientRepository;
import com.sai.recipeservice.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repository;

    @Autowired
    private MasterIngredientRepository masterRepo;

    public List<Recipe> getAllRecipes() {
        return repository.findAll();
    }

    public Recipe getRecipeById(Long id) {
        // Changed from getReferenceById to findById for better error handling
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));
    }

    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        // 1. Create a clean Recipe instance
        Recipe recipeToSave = new Recipe();
        recipeToSave.setName(recipe.getName());
        recipeToSave.setInstructions(recipe.getInstructions());
        recipeToSave.setIngredients(recipe.getIngredients());

        if (recipeToSave.getIngredients() != null) {
            for (Ingredient ing : recipeToSave.getIngredients()) {

                // 2. LINK: Link the map entity back to the parent Recipe
                // This ensures the RECIPE_ID column is populated
                ing.setRecipe(recipeToSave);

                // 3. SYNC MASTER: Handle the MasterIngredient relationship
                // Since the name field was removed from Ingredient, we use the name
                // provided in the MasterIngredient object sent in the request.
                String ingredientName = ing.getMasterIngredient().getName().toLowerCase();

                MasterIngredient master = masterRepo.findById(ingredientName.toLowerCase())
                        .orElseGet(() -> {
                            // Create and save new MasterIngredient if it doesn't exist
                            // Initialize the list to avoid NullPointerException later
                            return masterRepo.save(new MasterIngredient(ingredientName, new ArrayList<>()));
                        });

                // 4. ESTABLISH BIDIRECTIONAL LINK:
                // Link the specific occurrence to the master entry
                ing.setMasterIngredient(master);

                // (Optional) Update the master's list in memory
                if (master.getIngredientOccurrences() == null) {
                    master.setIngredientOccurrences(new ArrayList<>());
                }
                master.getIngredientOccurrences().add(ing);
            }
        }

        return repository.save(recipeToSave);
    }

    /**
     * New Method: Get all Recipe IDs linked to a specific ingredient name
     */
    public List<Long> getRecipeIdsByIngredient(String ingredientName) {
        return masterRepo.findById(ingredientName)
                .map(master -> master.getIngredientOccurrences().stream()
                        .map(occ -> occ.getRecipe().getId())
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    public String deleteRecipe(Long id) {
        Recipe recipe = getRecipeById(id);
        repository.delete(recipe);
        return recipe.getName() + " is deleted successfully";
    }
}
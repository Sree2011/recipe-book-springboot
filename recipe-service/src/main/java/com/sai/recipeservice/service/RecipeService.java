package com.sai.recipeservice.service;

import com.sai.recipeservice.entity.Ingredient;
import com.sai.recipeservice.entity.MasterIngredient;
import com.sai.recipeservice.entity.Recipe;
import com.sai.recipeservice.exception.RecipeNotFoundException;
import com.sai.recipeservice.repository.MasterIngredientRepository;
import com.sai.recipeservice.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }

    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        // 1. Create a clean Recipe instance
        Recipe recipeToSave = new Recipe();
        recipeToSave.setName(recipe.getName());
        recipeToSave.setServings(recipe.getServings());
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

    public List<String> getAllMasterIngredients() {
        List<MasterIngredient> master = masterRepo.findAll();
        List<String> names = new ArrayList<>();
        for (MasterIngredient m : master) {
            names.add(m.getName());
        }
        return names;

    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipeDto) {
        return repository.findById(id)
                .map(existingRecipe -> {
                    // Update basic fields if provided
                    if (updatedRecipeDto.getName() != null && !updatedRecipeDto.getName().isBlank()) {
                        existingRecipe.setName(updatedRecipeDto.getName());
                    }
                    if (updatedRecipeDto.getServings() != null) {
                        existingRecipe.setServings(updatedRecipeDto.getServings());
                    }
                    if (updatedRecipeDto.getInstructions() != null && !updatedRecipeDto.getInstructions().isBlank()) {
                        existingRecipe.setInstructions(updatedRecipeDto.getInstructions());
                    }

                    // ✅ Mutate the existing list instead of replacing it
                    List<Ingredient> ingredients = existingRecipe.getIngredients();

                    if (updatedRecipeDto.getIngredients() != null && !updatedRecipeDto.getIngredients().isEmpty()) {
                        for (Ingredient ingDto : updatedRecipeDto.getIngredients()) {
                            // Ensure master ingredient exists
                            MasterIngredient master = masterRepo
                                    .findByName(ingDto.getMasterIngredient().getName())
                                    .orElseGet(() -> masterRepo.save(
                                            new MasterIngredient(ingDto.getMasterIngredient().getName())
                                    ));

                            // Check if ingredient already exists in recipe
                            Optional<Ingredient> existingIngOpt = ingredients.stream()
                                    .filter(e -> e.getMasterIngredient().getName().equalsIgnoreCase(master.getName()))
                                    .findFirst();

                            if (existingIngOpt.isPresent()) {
                                // Update existing ingredient
                                Ingredient existingIng = existingIngOpt.get();
                                if (ingDto.getQuantity() != null) existingIng.setQuantity(ingDto.getQuantity());
                                if (ingDto.getUnit() != null) existingIng.setUnit(ingDto.getUnit());
                            } else {
                                // Add new ingredient
                                Ingredient newIng = new Ingredient();
                                newIng.setMasterIngredient(master);
                                newIng.setQuantity(ingDto.getQuantity());
                                newIng.setUnit(ingDto.getUnit());
                                newIng.setRecipe(existingRecipe); // maintain bidirectional link
                                ingredients.add(newIng);
                            }
                        }
                    }

                    return repository.save(existingRecipe);
                })
                .orElseThrow(() -> new RecipeNotFoundException(id));
    }











    }











package com.nandini.recipevault.ingredient;

import com.nandini.recipevault.exception.IngredientNotFoundException;


import java.util.List;

public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllCategories() {
        return ingredientRepository.findAll();
    }
    public Ingredient getIngredientById(long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public Ingredient saveIngredient(Ingredient Ingredient) {
        return ingredientRepository.save(Ingredient);
    }

    public void deleteIngredientById(long id) {
        ingredientRepository.deleteById(id);
    }
}

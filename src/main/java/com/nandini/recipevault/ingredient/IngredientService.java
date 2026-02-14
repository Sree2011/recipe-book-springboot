package com.nandini.recipevault.ingredient;

import com.nandini.recipevault.exception.IngredientNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
    public Ingredient getIngredientById(long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredientById(long id) {
        ingredientRepository.deleteById(id);
    }
}

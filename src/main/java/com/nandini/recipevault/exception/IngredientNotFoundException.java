package com.nandini.recipevault.exception;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Long id) {

        super("Ingredient not found "+ id);
    }
}

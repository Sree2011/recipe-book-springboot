package com.sai.recipeservice.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(Long id) {

        super("Recipe with id "+ id + " not found");
    }
}

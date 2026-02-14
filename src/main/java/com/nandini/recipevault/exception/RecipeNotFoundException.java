package com.nandini.recipevault.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(Long id) {

        super("Recipe not found: " + id);
    }
}

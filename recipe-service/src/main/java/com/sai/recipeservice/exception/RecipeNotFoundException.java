package com.sai.recipeservice.exception;

/**
 * Domain-specific runtime exception thrown when a requested recipe entity
 * cannot be located within the persistent storage database layer.
 * <p>
 * This exception extends {@code RuntimeException}, allowing it to bypass explicit,
 * mandatory checked exception method signatures. When thrown, it is intercepted
 * globally by the {@code GlobalExcHandler} component to format client-safe HTTP
 * error response payloads.
 * </p>
 *
 * @author Sree Sai Nandini Gundraju
 * @version 1.0
 * @see RuntimeException
 * @see GlobalExcHandler
 */
public class RecipeNotFoundException extends RuntimeException {

    /**
     * Constructs a new stateful exception instance populating a standardized,
     * descriptive error message string embedding the missing resource identifier key.
     *
     * @param id the unique primary key identifier of the recipe that could not be found
     */
    public RecipeNotFoundException(Long id) {
        super("Recipe with id "+ id + " not found");
    }
}
package com.sai.recipeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Central interceptor and global exception handling advisory layer for the recipe service microservice.
 * <p>
 * This class applies unified cross-cutting concern routing rules across all registered
 * controller endpoints. It intercepts runtime anomalies, prevents raw stack traces from leaking
 * down to client transport streams, and formats error messages into predictable, standardized JSON map structures.
 * </p>
 *
 * @author Sree Sai Nandini Gundraju
 * @version 1.0
 * @see ControllerAdvice
 */
@ControllerAdvice
public class GlobalExcHandler {

    /**
     * Intercepts targeted domain failures triggered when a requested recipe resource cannot be found.
     * <p>
     * Translates business-layer {@code RecipeNotFoundException} scenarios into structured,
     * client-safe error objects mapped to standard web responses.
     * </p>
     *
     * @param rx the runtime domain exception container holding the context of the missing resource
     * @return a {@code ResponseEntity} enclosing an error message map payload alongside an HTTP status of {@code 404 Not Found}
     */
    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleRecipeNotFound(RecipeNotFoundException rx){
        Map<String, String> error = new HashMap<>();
        error.put("error","Recipe not found");
        error.put("message",rx.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Catches and safely processes all generic unhandled runtime anomalies or system errors.
     * <p>
     * Acts as a global safety-net boundary to ensure that any unexpected backend breakdowns,
     * database integration connectivity faults, or sudden syntax violations are gracefully logged
     * and scrubbed of raw platform components before response serialization.
     * </p>
     *
     * @param ex the raw base exception instance captured during execution processing
     * @return a {@code ResponseEntity} enclosing an generalized error map payload alongside an HTTP status of {@code 500 Internal Server Error}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
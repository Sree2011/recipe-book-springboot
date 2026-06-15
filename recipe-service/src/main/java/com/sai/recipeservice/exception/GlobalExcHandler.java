package com.sai.recipeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExcHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleRecipeNotFound(RecipeNotFoundException rx){
        Map<String, String> error = new HashMap<>();
        error.put("error","Recipe not found");
        error.put("message",rx.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

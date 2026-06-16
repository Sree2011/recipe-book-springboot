package com.sai.scalingservice.controller;

import com.sai.scalingservice.dto.RecipeDTO;
import com.sai.scalingservice.service.ScalingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/scale")
@RequiredArgsConstructor
public class ScalingController {

    private final ScalingService scalingService;

    /**
     * Endpoint to scale a recipe.
     * Usage: GET http://localhost:8082/api/scale/1?targetPortions=6
     */
    @GetMapping("/{id}")
    @Operation(summary="scale the recipe")
    public ResponseEntity<RecipeDTO> getScaledRecipe(
            @PathVariable Long id,
            @RequestParam int targetPortions) {

        RecipeDTO scaledRecipe = scalingService.getScaledRecipe(id, targetPortions);
        return ResponseEntity.ok(scaledRecipe);
    }

    @GetMapping("/required-calories")
    @Operation(summary = "calculate required calories based on height, weight, age and activity")
    public Map<String, String> requiredCalories(
            @RequestParam Double height,
            @RequestParam Double weight,
            @RequestParam Double age,
            @RequestParam String activity){
        return scalingService.requiredCalories(height, weight, age, activity);
    }


}
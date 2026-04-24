package com.sai.scalingservice.client;

import com.sai.scalingservice.dto.RecipeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name: any name for the client
// url: the EXACT location of your RecipeService
@FeignClient(name = "recipe-client", url = "http://localhost:8081")
public interface RecipeClient {

    @GetMapping("/api/recipes/{id}")
    RecipeDTO getRecipeById(@PathVariable("id") Long id);
}

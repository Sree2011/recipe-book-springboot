package com.sai.recipeservice;

import com.sai.recipeservice.dto.IngredientDTO;
import com.sai.recipeservice.entity.Ingredient;
import com.sai.recipeservice.entity.MasterIngredient;
import com.sai.recipeservice.entity.Recipe;

import com.sai.recipeservice.service.RecipeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class RecipeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner seedRecipes(RecipeService service) {
		return args -> {
			// 1. Create the DTOs (The "Blueprints")
			// We use these to transport the initial data
			List<IngredientDTO> chaiIngredientsDTO = List.of(
					new IngredientDTO("Milk", 1.5, "cup"),
					new IngredientDTO("Water", 1.0, "cup"),
					new IngredientDTO("Tea Powder", 2.0, "tsp"),
					new IngredientDTO("Crushed Ginger", 1.0, "inch"),
					new IngredientDTO("Sugar", 3.0, "tsp")
			);

			// 2. Map DTOs to Entities
			// Note: We create a partial MasterIngredient here with just the name.
			// The RecipeService.createRecipe logic will handle the "upsert" to the Master table.
			List<Ingredient> chaiEntities = chaiIngredientsDTO.stream()
					.map(dto -> {
						Ingredient ing = new Ingredient();
						ing.setMasterIngredient(new MasterIngredient(dto.name(), new ArrayList<>()));
						ing.setQuantity(dto.quantity());
						ing.setUnit(dto.unit());
						return ing;
					})
					.toList();

			// 3. Create the Recipe Entity
			Recipe chai = new Recipe(
					null, // ID (Hibernate will generate this)
					"Ginger Chai",
					"Boil water with ginger and tea. Add milk and sugar. Bring to a boil twice.",
					chaiEntities
			);

			// 4. Save via Service
			// CRITICAL: Use service.createRecipe(chai) instead of repository.save(chai).
			// This ensures the MasterIngredient linking and recipe_id mapping logic is executed.
			service.createRecipe(chai);

			System.out.println("--- Seeding Recipe: " + chai.getName() + " ---");
		};
	}

}

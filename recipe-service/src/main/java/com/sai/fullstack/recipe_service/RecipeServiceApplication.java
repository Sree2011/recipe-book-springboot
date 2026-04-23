package com.sai.fullstack.recipe_service;

import com.sai.fullstack.recipe_service.dto.IngredientDTO;
import com.sai.fullstack.recipe_service.entity.Ingredient;
import com.sai.fullstack.recipe_service.entity.Recipe;
import com.sai.fullstack.recipe_service.repository.RecipeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class RecipeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner seedRecipes(RecipeRepository repository) { // Inject your repo here
		return args -> {
			// 1. Create the DTOs (The "Blueprints")
			List<IngredientDTO> chaiIngredientsDTO = List.of(
					new IngredientDTO("Milk", 1.5, "cup"),
					new IngredientDTO("Water", 1.0, "cup"),
					new IngredientDTO("Tea Powder", 2.0, "tsp"),
					new IngredientDTO("Crushed Ginger", 1.0, "inch"),
					new IngredientDTO("Sugar", 3.0, "tsp")
			);

			// 2. Map DTOs to Entities (The "Database Objects")
			List<Ingredient> chaiEntities = chaiIngredientsDTO.stream()
					.map(dto -> new Ingredient(dto.name(), dto.quantity(), dto.unit()))
					.toList();

			// 3. Create the Recipe Entity
			Recipe chai = new Recipe(
					null, // ID (Hibernate will generate this)
					"Ginger Chai",
					"Boil water with ginger and tea. Add milk and sugar. Bring to a boil twice.",
					chaiEntities
			);

			// 4. Save to DB
			repository.save(chai);

			System.out.println("--- Seeding Recipe: " + chai.getName() + " ---");
		};
	}
}

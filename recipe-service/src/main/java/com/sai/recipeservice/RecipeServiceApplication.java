
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

/**
 * Root bootstrap launcher and configuration center for the Recipe Core Microservice.
 * <p>
 * This class serves as the primary initialization entry point for the application runtime.
 * It activates core Spring Boot auto-configuration, initializes the application context,
 * and enables declarative REST client routing wrappers via Spring Cloud OpenFeign.
 * </p>
 *
 * @author Sree Sai Nandini Gundraju
 * @version 1.0
 * @see SpringBootApplication
 * @see EnableFeignClients
 */
@SpringBootApplication
@EnableFeignClients
public class RecipeServiceApplication {

	/**
	 * Primary executable entry point used to launch the Spring application context.
	 * Delegates bootstrap controls directly to the underlying framework engine.
	 *
	 * @param args command-line execution arguments passed to the application runtime
	 */
	public static void main(String[] args) {
		SpringApplication.run(RecipeServiceApplication.class, args);
	}

	/**
	 * Configures a startup bean initialization task runner to automatically provision
	 * a persistent sandbox dataset inside the MySQL schema context upon container boot.
	 * <p>
	 * **Data Seeding Lifecycle Strategy:**
	 * Maps standalone programmatic DTO constructs directly to formal mapping models,
	 * packaging an initial "Ginger Chai" demonstration recipe. The operational execution routing
	 * is explicitly passed through the business {@code RecipeService} layer instead of the
	 * raw repository interfaces, ensuring cascade mappings and global master dictionary upsert
	 * hooks are evaluated successfully.
	 * </p>
	 *
	 * @param service the core business logic handling manager instance injected into the execution context
	 * @return an operational {@code CommandLineRunner} execution block evaluated by the framework at startup
	 */
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
					null, // ID (Hibernate will generate this),
					2,
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


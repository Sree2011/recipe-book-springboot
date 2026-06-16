
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
}




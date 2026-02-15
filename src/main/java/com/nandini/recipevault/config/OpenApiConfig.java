package com.nandini.recipevault.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI recipeVaultOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Recipe Vault API")
                        .description("API for managing recipes, ingredients, and categories")
                        .version("1.0.0"));
    }
}
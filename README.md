# Recipe Book Microservices

This project is a Spring Boot-based microservices application designed to manage recipes and handle ingredient scaling. It leverages a microservices architecture to separate concerns between recipe management and scaling logic.


## Project Overview

The system consists of two primary services:
* **Recipe Service**: Manages the core recipe data, including ingredients and instructions. It maintains a master list of ingredients to ensure consistency across different recipes.
* **Scaling Service**: A dedicated service intended to handle the logic for scaling ingredient quantities based on portion sizes.

## Technical Stack

* **Java**: 25
* **Framework**: Spring Boot 4.0.6-SNAPSHOT
* **Build Tool**: Maven with Wrapper
* **Database**: H2 In-Memory Database (for development)
* **Communication**: Spring Cloud OpenFeign
* **Documentation**: SpringDoc OpenAPI (Swagger UI)
* **Utilities**: Project Lombok

## Project Structure

# Repository Structure

```
├── recipe-service
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── sai
│   │       │           └── fullstack
│   │       │               └── recipe_service
│   │       │                   ├── controller
│   │       │                   ├── dto
│   │       │                   ├── entity
│   │       │                   ├── repository
│   │       │                   ├── service
│   │       │                   └── RecipeServiceApplication.java
│   │       └── resources
│   │           └── application.properties
│   ├── .gitattributes
│   ├── .gitignore
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
├── scaling-service
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── sai
│   │       │           └── fullstack
│   │       │               └── scaling_service
│   │       │                   ├── client
│   │       │                   └── ScalingServiceApplication.java
│   │       └── resources
│   │           └── application.properties
│   ├── .gitattributes
│   ├── .gitignore
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml
├── _config.yml
└── README.md
```


## Features

### Recipe Management
* **Create Recipes**: Add new recipes with detailed instructions and specific ingredient quantities.
* **Master Ingredient Sync**: Automatically tracks unique ingredients in a `master_ingredients` table to link multiple recipes to the same ingredient entity.
* **Automated Seeding**: The system includes a `CommandLineRunner` that seeds an initial "Ginger Chai" recipe upon startup for testing.

### API Endpoints
The Recipe Service exposes several REST endpoints:
* `POST /api/recipes/create`: Creates a new recipe and syncs ingredients.
* `GET /api/recipes/getall`: Retrieves all stored recipes.
* `GET /api/recipes/{id}`: Retrieves a specific recipe by its ID.
* `DELETE /api/recipes/{id}`: Deletes a recipe.
* `GET /api/recipes/ingredient/{name}/recipes`: Finds all recipe IDs that contain a specific ingredient.


## Configuration

### Recipe Service (`8081`)
* **Port**: 8081
* **H2 Console**: Accessible at `/h2-console`
* **Swagger UI**: Accessible at `/swagger-ui.html` for API testing

### Scaling Service (`8082`)
* **Port**: 8082 (configured to avoid clashes with the Recipe Service)

## Getting Started

1.  **Prerequisites**: Ensure you have JDK 21 installed.
2.  **Build**: Use the provided Maven wrapper to build the services.
    ```bash
    ./mvnw clean install
    ```
3.  **Run**:
    * Navigate to the `recipe-service` directory and run: `./mvnw spring-boot:run`
    * Navigate to the `scaling-service` directory and run: `./mvnw spring-boot:run`
4.  **Verify**: Access the H2 console at `http://localhost:8081/h2-console` using the username `sa`, leave the password blank
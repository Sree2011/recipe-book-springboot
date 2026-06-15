package com.sai.recipeservice.exception;
public class SwaggerConstants {

    public static final String RECIPE404 = """
        {
          "timestamp": "2026-06-15T16:24:52Z",
          "status": 404,
          "error": "Not Found",
          "message": "The requested recipe with the given ID could not be found.",
          "path": "/api/v1/recipes/123"
        }
        """;

    public static final String RECIPE500 = """
        {
          "timestamp": "2026-06-15T16:24:52Z",
          "status": 500,
          "error": "Internal Server Error",
          "message": "An unexpected error occurred while processing the recipe request.",
          "path": "/api/v1/recipes/123"
        }
        """;
    public static final String RECIPE200 = """
            {
              "id": 0,
              "servings": 0,
              "name": "string",
              "instructions": "string",
              "ingredients": [
                {
                  "id": 0,
                  "quantity": 0.1,
                  "unit": "string",
                  "masterIngredient": {
                    "name": "string"
                  },
                  "recipe": {}
                }
              ]
            }
            """;

    public static final String ALLRECIPES = """
            {
            {
              "id": 0,
              "servings": 0,
              "name": "string",
              "instructions": "string",
              "ingredients": [
                {
                  "id": 0,
                  "quantity": 0.1,
                  "unit": "string",
                  "masterIngredient": {
                    "name": "string"
                  },
                  "recipe": {}
                }
              ]
            },
            {
              "id": 1,
              "servings": 0,
              "name": "string",
              "instructions": "string",
              "ingredients": [
                {
                  "id": 0,
                  "quantity": 0.1,
                  "unit": "string",
                  "masterIngredient": {
                    "name": "string"
                  },
                  "recipe": {}
                }
              ]
            }
            """;
}

package com.nandini.recipevault.config;

public class Constants {

    public static final String exampleRecipeJson = """
                            {
                              "name": "Chocolate Cake",
                              "description": "A rich and moist chocolate cake",
                              "instructions": "Mix flour, sugar, cocoa...",
                              "ingredients": [
                                { "name": "Sugar", "quantity": "2 tbsp" },
                                { "name": "Flour", "quantity": "1 cup" }
                              ]
                            }
                            """;
    public static final String Recipe404 =
            """
        {
            "error":"Recipe not found",
            "status":404,
            "timestamp":"2026-02-14T22:45:00Z"
        }
        """;

    public static final String Recipe500 =
            """
        {
            "error":"Internal Server Error",
            "status":500,
            "timestamp":"2026-02-14T22:45:00Z"
        }
        """;



}

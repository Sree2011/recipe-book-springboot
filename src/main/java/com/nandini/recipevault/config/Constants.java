package com.nandini.recipevault.config;

public class Constants {

    private Constants() {}

    public static final String EXAMPLERECIPEJSON = """
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

    public static final String EXAMPLEINGREDIENTJSON = """
                                        {
                                            "name" : "Turmeric powder",
                                            "quantity" : "2 tbsp"
                                        }
                                    """;


    public static final String INGREDIENT404 =
                                                    """
                                        {
                                        "error":"Ingredient not found",
                                        "status":404,
                                        "timestamp":"2026-02-14T22:45:00Z"
                                        }
                                        """;

    public static final String RECIPE404 =
            """
        {
            "error":"Recipe not found",
            "status":404,
            "timestamp":"2026-02-14T22:45:00Z"
        }
        """;

    public static final String CATEGORY404 =
            """
        {
            "error":"Category not found",
            "status":404,
            "timestamp":"2026-02-14T22:45:00Z"
        }
        """;

    public static final String RECIPE500 =
            """
        {
            "error":"Internal Server Error",
            "status":500,
            "timestamp":"2026-02-14T22:45:00Z"
        }
        """;

    public static final String RECIPE204 = """
            {
            "status": "success",
            "message": "Recipe deleted successfully"
}
""";

    public static final String INGREDIENT204 = """
            {
            "status": "success",
            "message": "Ingredient deleted successfully"
}
""";

    public static final String CATEGORY204 = """
            {
            "status": "success",
            "message": "Category deleted successfully"
}
""";



}

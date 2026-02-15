package com.nandini.recipevault.ingredient;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Schema representing an ingredient with name and quantity")
public class IngredientDto {

        @Schema(description = "ID of the ingredient", example = "1")
        private Long id;
        @Schema(description = "Name of the ingredient", example = "Sugar")
        private String name;

        @Schema(description = "Quantity of the ingredient", example = "2 tbsp")
        private String quantity;


        public IngredientDto(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public IngredientDto() {}

    public IngredientDto(Long id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}

package com.nandini.recipevault.category;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) for {@link Category}.
 *
 * <p>Used to transfer category data between layers
 * without exposing the entity directly.
 * -----
 * Example:
 * <pre>
 * CategoryDto dto = new CategoryDto("Desserts", "10 recipes");
 * </pre>
 */
@Schema(description = "DTO representing a recipe category with name and quantity")
public class CategoryDto {

    @Schema(description = "Name of the category", example = "Desserts")
    private String name;

    @Schema(description = "Quantity or count associated with the category", example = "10 recipes")
    private String quantity;

    public CategoryDto(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
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
        return "CategoryDto{" +
                "name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
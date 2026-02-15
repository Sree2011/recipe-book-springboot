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

    @Schema(description = "ID of the category")
    private Long id;
    @Schema(description = "Name of the category", example = "Desserts")
    private String name;



    public CategoryDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "CategoryDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
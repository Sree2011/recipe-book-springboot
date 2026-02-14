/**
 * Data Transfer Object (DTO) for {@link Category}.
 *
 * <p>Used to transfer category data between layers
 * without exposing the entity directly.
 *
 * Example:
 * <pre>
 * CategoryDTO dto = new CategoryDTO("Desserts");
 * </pre>
 */

package com.nandini.recipevault.category;

public class CategoryDto {
    private String name;
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

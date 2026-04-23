package com.sai.fullstack.recipe_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe_ingredients_map")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique ID for this specific mapping

    private String name; // This links to your Master list
    private double quantity;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe; // Links back to the Recipe

    public Ingredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
}
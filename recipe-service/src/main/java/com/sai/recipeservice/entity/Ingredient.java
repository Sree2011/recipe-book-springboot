package com.sai.recipeservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;

    private double quantity;
    private String unit;

    // Corrected: Mapping to the MasterIngredient entity instead of a String
    @ManyToOne
    @JoinColumn(name = "ingredient_name", referencedColumnName = "name")
    private MasterIngredient masterIngredient;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    // Updated Constructor to accept the MasterIngredient object
    public Ingredient(MasterIngredient masterIngredient, double quantity, String unit) {
        this.masterIngredient = masterIngredient;
        this.quantity = quantity;
        this.unit = unit;
    }
}
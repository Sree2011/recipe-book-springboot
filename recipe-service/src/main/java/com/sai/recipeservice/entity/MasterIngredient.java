package com.sai.recipeservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "master_ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterIngredient {
    @Id
    private String name; // "Potato", "Turmeric" (The one and only)

    // This links the master ingredient to all its occurrences in the map table
    @OneToMany(mappedBy = "masterIngredient")
    @JsonIgnore
    // Simple constructor for seeding
    private List<Ingredient> ingredientOccurrences;

    public MasterIngredient(String name) {
        this.name = name;
    }
}
package com.sai.recipeservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * JPA Entity representing the global master ingredient dictionary registry.
 * <p>
 * This class acts as the single source of truth for ingredient definitions within the MySQL schema.
 * It enforces uniqueness by using the natural text name of the ingredient as its primary key identifier,
 * eliminating duplicate resource creation and orchestrating state parity across the microservice ecosystem.
 * </p>
 *
 * @author Sree Sai Nandini Gundraju
 * @version 1.0
 * @see Ingredient
 */
@Entity
@Table(name = "master_ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterIngredient {

    /**
     * The unique textual name of the ingredient serving as the natural primary key identifier.
     * Examples include "potato", "turmeric", or "ginger".
     */
    @Id
    private String name; // "Potato", "Turmeric" (The one and only)

    /**
     * Bidirectional one-to-many relationship tracking every occurrence where this global asset
     * is referenced across different recipe ingredient map layouts.
     * <p>
     * Managed via {@code @JsonIgnore} to prevent cyclic recursion loops when serializing
     * database queries down to JSON strings over transport layers.
     * </p>
     */
    @OneToMany(mappedBy = "masterIngredient")
    @JsonIgnore
    private List<Ingredient> ingredientOccurrences;

    /**
     * Overloaded constructor used for automated database seeding initialization,
     * sandbox provisioning, or quick key lookup generation without populating relational lists.
     *
     * @param name the precise unique string name descriptor for the master entry
     */
    public MasterIngredient(String name) {
        this.name = name;
    }
}
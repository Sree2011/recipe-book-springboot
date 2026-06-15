
package com.sai.recipeservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA Entity representing a contextual cooking assignment of a specific ingredient within a recipe.
 * <p>
 * This class acts as an intermediate relational join table mapping rows in the MySQL storage layout.
 * It tracks local quantitative metrics (quantity, measurement units) for a cooking instance while linking 
 * dynamically to a global immutable lookup entity {@code MasterIngredient} and an owning parent {@code Recipe}.
 * </p>
 * * @author Sree Sai Nandini Gundraju
 * @version 1.0
 * @see Recipe
 * @see MasterIngredient
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recipe_ingredients_map")
public class Ingredient {

    /**
     * The auto-incremented primary key identifier for the ingredient mapping record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The quantitative decimal amount of the ingredient required for the associated recipe.
     */
    private Double quantity;

    /**
     * The metric or scalar unit of measurement representation (e.g., "grams", "ml", "tsp").
     */
    private String unit;

    /**
     * Unidirectional many-to-one relationship pointing to the core global ingredient asset descriptor.
     * <p>
     * Maps the local relational record column to the unique natural key name registry index 
     * inside the master inventory table.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "ingredient_name", referencedColumnName = "name")
    private MasterIngredient masterIngredient;

    /**
     * Bidirectional many-to-one relationship linking back to the parent recipe container.
     * <p>
     * Managed via {@code @JsonBackReference} boundary rules to intentionally omit serializing 
     * the parent object string payload back down the wire, shielding the application runtime 
     * from circular dependency recursion traps.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    /**
     * Overloaded stateful constructor used to initialize a new transient or detached 
     * ingredient relationship item wrapper without mapping database key parameters.
     *
     * @param masterIngredient the unified persistent master asset instance to map against
     * @param quantity the precise measurement amount required
     * @param unit the descriptive unit type string
     */
    public Ingredient(MasterIngredient masterIngredient, Double quantity, String unit) {
        this.masterIngredient = masterIngredient;
        this.quantity = quantity;
        this.unit = unit;
    }
}


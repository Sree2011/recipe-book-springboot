package com.sai.recipeservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * JPA Entity representing a complete culinary recipe card within the persistent storage engine.
 * <p>
 * This class serves as the primary aggregate root for recipe records in the MySQL database system.
 * It encapsulates the high-level description data (such as title, instructions, and yield sizes)
 * and controls a bidirectional one-to-many relationship mapping down to child ingredient line items.
 * </p>
 *
 * @author Sree Sai Nandini Gundraju
 * @version 1.0
 * @see Ingredient
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Recipes")
public class Recipe {

    /**
     * The auto-incremented primary key identifier for the recipe entity record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The standard base portion yield or total number of servings this recipe card makes.
     */
    private Integer servings;

    /**
     * The descriptive name or title of the dish (e.g., "Ginger Chai").
     */
    private String name;

    /**
     * The textual step-by-step preparation and cooking instructions for preparing the dish.
     */
    private String instructions;

    /**
     * Bidirectional one-to-many collection containing the precise contextual breakdown
     * of individual ingredients mapped directly to this recipe card wrapper.
     * <p>
     * **Relational Rules Strategy:**
     * Configured with {@code CascadeType.ALL} and {@code orphanRemoval = true} to guarantee
     * that all saving, updating, and deleting operations on the parent recipe cascade cleanly
     * to its ingredient mapping rows in the MySQL schema.
     * Managed via {@code @JsonManagedReference} to cleanly control nested jackson forward
     * serialization streams.
     * </p>
     */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Ingredient> ingredients;
}
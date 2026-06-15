package com.sai.recipeservice.repository;

import com.sai.recipeservice.entity.MasterIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Data access repository interface providing abstraction layer mechanics
 * for managing {@code MasterIngredient} persistence logic inside the MySQL engine.
 * <p>
 * By extending {@code JpaRepository}, this component inherits core out-of-the-box
 * CRUD operations, sorting utilities, and transactional data capabilities. It maps
 * data using the natural key {@code String} name as its underlying row identifier.
 * </p>
 *
 * @author Sree Sai Nandini Gundraju
 * @version 1.0
 * @see JpaRepository
 * @see MasterIngredient
 */
public interface MasterIngredientRepository extends JpaRepository<MasterIngredient,String> {

    /**
     * Looks up a singular master ingredient definition record by searching
     * against its natural unique textual name property.
     * <p>
     * Wraps the results inside an {@code Optional} container to safely signal
     * the possible absence of matching records down to consuming service classes,
     * preventing downstream NullPointerExceptions.
     * </p>
     *
     * @param name the exact unique string name descriptor of the master asset to find
     * @return an {@code Optional} containing the located {@code MasterIngredient},
     * or an empty {@code Optional} if no database match exists
     */
    Optional<MasterIngredient> findByName(String name);
}
package com.sai.recipeservice.repository;

import com.sai.recipeservice.entity.MasterIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterIngredientRepository extends JpaRepository<MasterIngredient,String> {
    Optional<MasterIngredient> findByName(String name);
}

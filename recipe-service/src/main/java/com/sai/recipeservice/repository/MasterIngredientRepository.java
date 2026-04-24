package com.sai.recipeservice.repository;

import com.sai.recipeservice.entity.MasterIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterIngredientRepository extends JpaRepository<MasterIngredient,String> {
}

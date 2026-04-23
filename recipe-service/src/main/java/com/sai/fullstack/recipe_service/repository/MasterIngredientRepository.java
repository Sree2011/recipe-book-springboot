package com.sai.fullstack.recipe_service.repository;

import com.sai.fullstack.recipe_service.entity.MasterIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterIngredientRepository extends JpaRepository<MasterIngredient,String> {
}

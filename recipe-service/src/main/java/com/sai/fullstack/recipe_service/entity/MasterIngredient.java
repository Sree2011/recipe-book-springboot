package com.sai.fullstack.recipe_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "master_ingredients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterIngredient {
    @Id
    private String name; // "Potato", "Turmeric" (The one and only)
}
package com.sai.recipeservice.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "food_calories")
public class FoodCalories {


    @Id
    @Column(name="food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_name", nullable = false, length = 100)
    private String foodName;

    @Column(name = "serving_size", length = 50)
    private String servingSize;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "carbs")
    private Double carbs;

    @Column(name = "fat")
    private Double fat;



}

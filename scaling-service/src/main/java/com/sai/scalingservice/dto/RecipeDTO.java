package com.sai.scalingservice.dto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipeDTO {

        private Long id;
        private int servings;
        private String name;
        private String instructions;
        private List<IngredientDTO> ingredients;


}

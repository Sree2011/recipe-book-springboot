package com.sai.scalingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

        private Long id;
        private double quantity;
        private String unit;
        private MasterIngredientDTO masterIngredient;

}

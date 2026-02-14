package com.nandini.recipevault.category;

import com.nandini.recipevault.recipe.RecipeMapper;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = RecipeMapper.class)
public interface CategoryMapper {
    CategoryDto toDTO(Category category);
    Category toEntity(CategoryDto categoryDTO);

    List<CategoryDto> toDTOList(List<Category> categories);
    List<Category> toEntityList(List<CategoryDto> categoryDTOs);
}
/**
 * Mapper interface for converting between {@link Category} entities
 * and {@link CategoryDTO} objects.
 *
 * <p>This mapper helps decouple the persistence layer from the API layer
 * by providing compile-time safe conversions using MapStruct.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Convert {@link Category} to {@link CategoryDTO}</li>
 *   <li>Convert {@link CategoryDTO} to {@link Category}</li>
 *   <li>Handle collections of categories and DTOs</li>
 * </ul>
 *
 * Example usage:
 * <pre>
 * CategoryDTO dto = categoryMapper.toDto(categoryEntity);
 * Category entity = categoryMapper.toEntity(dto);
 * </pre>
 *
 * <p>Note: This interface is typically implemented automatically
 * by MapStruct at compile time.
 */

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
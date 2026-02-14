package com.nandini.recipevault.category;

import com.nandini.recipevault.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for managing {@link Category} entities.
 *
 * <p>Encapsulates business logic related to categories,
 * such as creating, updating, and retrieving categories.
 * -------
 * Example:
 * <pre>
 * categoryService.createCategory("Desserts");
 * </pre>
 */
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategoryById(long id) {
        categoryRepository.deleteById(id);
    }

}

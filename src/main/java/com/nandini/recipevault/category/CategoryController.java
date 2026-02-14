/**
 * REST controller for managing categories.
 *
 * <p>Exposes endpoints for creating, retrieving,
 * updating, and deleting categories.
 * -----
 * Example usage:
 * <pre>
 * POST /api/categories
 * {
 *   "name": "Desserts"
 * }
 * </pre>
 */
package com.nandini.recipevault.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link Category} entities.
 *
 * <p>Provides endpoints for creating, retrieving, updating,
 * and deleting categories in the Recipe Vault application.
 * -----
 * Example usage:
 * <pre>
 * POST /api/categories
 * {
 *   "name": "Desserts"
 * }
 * </pre>
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category) {
        Category existing = categoryService.getCategoryById(id);
        existing.setName(category.getName());
        Category updated = categoryService.saveCategory(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
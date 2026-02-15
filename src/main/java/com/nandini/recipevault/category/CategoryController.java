package com.nandini.recipevault.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nandini.recipevault.config.Constants.*;

@RestController
@RequestMapping("/api/categories")
@ApiResponse(responseCode = "500", description = "Internal Server Error",
        content = @Content(mediaType = "application/json",
                examples = @ExampleObject(name = "Category 500", value = RECIPE500)))
@ApiResponse(responseCode = "404", description = "Category Not Found",
        content = @Content(mediaType = "application/json",
                examples = @ExampleObject(name = "Category 404", value = CATEGORY404)))
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    // Get all categories
    @Operation(summary = "Get All Categories")
    @ApiResponse(responseCode = "200", description = "All categories retrieved successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Category Example", value = "{ \"id\": 1, \"name\": \"Desserts\" }")))
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> dtos = categoryService.getAllCategories()
                .stream()
                .map(categoryMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // Get category by ID
    @Operation(summary = "Get Category by ID")
    @ApiResponse(responseCode = "200", description = "Category retrieved successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Category Example", value = "{ \"id\": 1, \"name\": \"Desserts\" }")))
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryMapper.toDTO(category));
    }

    // Create a new category
    @Operation(summary = "Create a Category")
    @ApiResponse(responseCode = "200", description = "Category created successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Category Example", value = "{ \"id\": 1, \"name\": \"Desserts\" }")))
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(categoryMapper.toDTO(savedCategory));
    }

    // Update category by ID
    @Operation(summary = "Update Category by ID")
    @ApiResponse(responseCode = "200", description = "Category updated successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Category Example", value = "{ \"id\": 1, \"name\": \"Desserts\" }")))
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        Category existing = categoryService.getCategoryById(id);
        existing.setName(categoryDto.getName());
        Category updated = categoryService.saveCategory(existing);
        return ResponseEntity.ok(categoryMapper.toDTO(updated));
    }

    // Delete category by ID
    @Operation(summary = "Delete Category by ID")
    @ApiResponse(responseCode = "200", description = "Category deleted successfully",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Delete category", value = CATEGORY204)))
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(CATEGORY204);
    }
}
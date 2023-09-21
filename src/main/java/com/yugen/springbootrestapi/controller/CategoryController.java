package com.yugen.springbootrestapi.controller;

import com.yugen.springbootrestapi.payload.dto.CategoryDto;
import com.yugen.springbootrestapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CategoryController is a REST controller for category services.
 * It contains endpoints for adding, retrieving, updating, and deleting categories.
 */
@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    /**
     * The CategoryService to handle category services.
     */
    private final CategoryService categoryService;

    /**
     * This endpoint is used to add a new category.
     *
     * @param categoryDto A data transfer object containing the category details.
     * @return A ResponseEntity with the added CategoryDto and HTTP status.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    /**
     * This endpoint is used to retrieve a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return A ResponseEntity with the retrieved CategoryDto and HTTP status.
     */
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId) {
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    /**
     * This endpoint is used to retrieve all categories.
     *
     * @return A ResponseEntity with a list of all CategoryDtos and HTTP status.
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * This endpoint is used to update a category by its ID.
     *
     * @param categoryDto A data transfer object containing the updated category details.
     * @param categoryId  The ID of the category to update.
     * @return A ResponseEntity with the updated CategoryDto and HTTP status.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable("id") Long categoryId) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    /**
     * This endpoint is used to delete a category by its ID.
     *
     * @param categoryId The ID of the category to delete.
     * @return A ResponseEntity with a success message and HTTP status.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully!.");
    }
}

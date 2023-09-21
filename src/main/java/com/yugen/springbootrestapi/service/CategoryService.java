package com.yugen.springbootrestapi.service;

import com.yugen.springbootrestapi.payload.dto.CategoryDto;

import java.util.List;

/**
 * CategoryService is an interface for category services.
 * It contains methods for adding, retrieving, updating, and deleting categories.
 */
public interface CategoryService {

    /**
     * This method is used to add a new category.
     *
     * @param categoryDto A data transfer object containing the category details.
     * @return The added CategoryDto.
     */
    CategoryDto addCategory(CategoryDto categoryDto);

    /**
     * This method is used to retrieve a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The retrieved CategoryDto.
     */
    CategoryDto getCategory(Long categoryId);

    /**
     * This method is used to retrieve all categories.
     *
     * @return A list of all CategoryDtos.
     */
    List<CategoryDto> getAllCategories();

    /**
     * This method is used to update a category by its ID.
     *
     * @param categoryDto A data transfer object containing the updated category details.
     * @param categoryId  The ID of the category to update.
     * @return The updated CategoryDto.
     */
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    /**
     * This method is used to delete a category by its ID.
     *
     * @param categoryId The ID of the category to delete.
     */
    void deleteCategory(Long categoryId);
}

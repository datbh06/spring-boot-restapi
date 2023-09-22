package com.yugen.springbootrestapi.service.impl;

import com.yugen.springbootrestapi.entity.Category;
import com.yugen.springbootrestapi.exception.ResourceNotFoundException;
import com.yugen.springbootrestapi.payload.dto.CategoryDto;
import com.yugen.springbootrestapi.repository.CategoryRepository;
import com.yugen.springbootrestapi.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the CategoryService interface.
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    /**
     * This method is used to add a new category.
     *
     * @param categoryDto A data transfer object containing the category details.
     * @return The added CategoryDto.
     */
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    /**
     * This method is used to retrieve a category by its ID.
     *
     * @param categoryId The ID of the category to retrieve.
     * @return The retrieved CategoryDto.
     */
    @Override
    public CategoryDto getCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        return modelMapper.map(category, CategoryDto.class);
    }

    /**
     * This method is used to retrieve all categories.
     *
     * @return A list of all CategoryDtos.
     */
    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map((category) -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    /**
     * This method is used to update a category by its ID.
     *
     * @param categoryDto A data transfer object containing the updated category details.
     * @param categoryId  The ID of the category to update.
     * @return The updated CategoryDto.
     */
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        if (categoryDto.getName() != null) {
            category.setName(categoryDto.getName());
        }

        if (categoryDto.getDescription() != null) {
            category.setDescription(categoryDto.getDescription());
        }

        category.setId(categoryId);

        Category updatedCategory = categoryRepository.save(category);

        return modelMapper.map(updatedCategory, CategoryDto.class);
    }


    /**
     * This method is used to delete a category by its ID.
     *
     * @param categoryId The ID of the category to delete.
     */
    @Override
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        categoryRepository.delete(category);
    }
}

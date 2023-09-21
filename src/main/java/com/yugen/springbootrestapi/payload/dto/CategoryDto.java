package com.yugen.springbootrestapi.payload.dto;

import lombok.*;

/**
 * Represents a Data Transfer Object for the Category entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    /**
     * Unique identifier of the category.
     */
    private Long id;

    /**
     * Name of the category.
     */
    private String name;

    /**
     * Description of the category.
     */
    private String description;
}

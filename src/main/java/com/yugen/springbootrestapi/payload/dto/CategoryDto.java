package com.yugen.springbootrestapi.payload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Represents a Data Transfer Object for the Category entity.
 */
@Schema(
        description = "Data Transfer Object for the Category entity"
)
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
    @Schema(
            description = "Category Name"
    )
    private String name;

    /**
     * Description of the category.
     */
    @Schema(
            description = "Category Description"
    )
    private String description;
}

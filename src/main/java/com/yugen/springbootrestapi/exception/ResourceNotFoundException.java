package com.yugen.springbootrestapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a resource is not found.
 */
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Name of the resource that was not found.
     */
    private final String resourceName;

    /**
     * Name of the field that was queried.
     */
    private final String fieldName;

    /**
     * Value of the field that was queried.
     */
    private final String fieldValue;

    /**
     * Constructs a new runtime exception with a detail message indicating
     * the resource, field, and value that were not found. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param resourceName the name of the resource that was not found
     * @param fieldName    the name of the field that was queried
     * @param fieldValue   the value of the field that was queried
     */
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}

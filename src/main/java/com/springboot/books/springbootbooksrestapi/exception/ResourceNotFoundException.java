package com.springboot.books.springbootbooksrestapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException {

    // Fields to store information about the resource not found
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    // Constructor to initialize the exception with resource details
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {

        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
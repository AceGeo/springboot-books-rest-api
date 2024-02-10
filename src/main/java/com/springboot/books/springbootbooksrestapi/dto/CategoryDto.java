package com.springboot.books.springbootbooksrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "CategoryDto Model Information"
)
//Data Transfer Object (DTO) for representing category-related information.
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
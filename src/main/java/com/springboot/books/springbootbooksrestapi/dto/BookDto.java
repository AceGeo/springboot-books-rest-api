package com.springboot.books.springbootbooksrestapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(
        description = "BookDto Model Information"
)
//Data Transfer Object (DTO) for representing book-related information.
public class BookDto {

    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Book title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 6, message = "Book title should have at least 2 characters")
    private String author;
    @NotEmpty
    private String language;

    @NotEmpty
    private String pages;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @NotEmpty
    @Size(min= 10, message = "Movie description should have at least 10 characters")
    private String description;



}
